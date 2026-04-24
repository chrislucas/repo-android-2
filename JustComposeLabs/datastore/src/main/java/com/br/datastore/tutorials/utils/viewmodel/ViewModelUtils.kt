package com.br.datastore.tutorials.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.CoroutineContext

fun <V : ViewModel> createViewModel(
    viewModelStore: ViewModelStore,
    factory: ViewModelProvider.Factory,
    clazz: Class<V>
): V = ViewModelProvider(viewModelStore, factory)[clazz]


data class ConstructorArg(
    val type: Class<*>,
    val values: List<Any?>
) {
    init {
        require(values.isNotEmpty()) { "ConstructorArg requires at least one value." }
    }
}

class ViewModelFactory(
    private val constructorArgs: List<ConstructorArg> = emptyList(),
    private val creators: Map<Class<out ViewModel>, (CreationExtras) -> ViewModel> = emptyMap()
) : ViewModelProvider.Factory {

    /**
     * @see  androidx.lifecycle.viewmodel.ViewModelInitializer
     * @see  androidx.lifecycle.ViewModelProvider.Factory.Companion
     * https://developer.android.com/reference/androidx/lifecycle/viewmodel/ViewModelInitializer
     */

    constructor(vararg constructorArgs: ConstructorArg) : this(constructorArgs.toList(), emptyMap())

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        create(modelClass, CreationExtras.Empty)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        findCreator(modelClass)?.let { creator ->
            return creator(extras) as T
        }

        val expandedArgs = if (constructorArgs.isEmpty()) null else expandConstructorArgs()

        return try {
            if (expandedArgs == null) {
                modelClass.getDeclaredConstructor().newInstance()
            } else {
                val (parameterTypes, parameterValues) = expandedArgs
                modelClass.getDeclaredConstructor(*parameterTypes).newInstance(*parameterValues)
            }
        } catch (error: ReflectiveOperationException) {
            throw buildCreationError(modelClass, expandedArgs?.first ?: emptyArray(), error)
        }
    }

    private fun <T : ViewModel> findCreator(modelClass: Class<T>): ((CreationExtras) -> ViewModel)? {
        creators[modelClass]?.let { return it }
        return creators.entries.firstOrNull { (registeredClass, _) ->
            modelClass.isAssignableFrom(registeredClass)
        }?.value
    }

    private fun expandConstructorArgs(): Pair<Array<Class<*>>, Array<Any?>> {
        val expandedTypes = constructorArgs.flatMap { arg -> List(arg.values.size) { arg.type } }
        val expandedValues = constructorArgs.flatMap { it.values }
        return expandedTypes.toTypedArray() to expandedValues.toTypedArray()
    }

    private fun <T : ViewModel> buildCreationError(
        modelClass: Class<T>,
        parameterTypes: Array<Class<*>>,
        error: ReflectiveOperationException
    ): IllegalArgumentException {
        val providedSignature = if (parameterTypes.isEmpty()) {
            "()"
        } else {
            parameterTypes.joinToString(prefix = "(", postfix = ")") { it.simpleName }
        }
        val availableSignatures = modelClass.declaredConstructors.joinToString { constructor ->
            constructor.parameterTypes.joinToString(prefix = "(", postfix = ")") { it.simpleName }
        }
        return IllegalArgumentException(
            "Unable to create ${modelClass.name} with signature $providedSignature. " +
                "Available constructors: $availableSignatures",
            error
        )
    }

    companion object {
        inline fun <reified T> arg(vararg values: T): ConstructorArg =
            ConstructorArg(T::class.java, values.toList())
    }
}


abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val viewModelSupervisorJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + viewModelSupervisorJob

    override fun onCleared() {
        super.onCleared()
        viewModelSupervisorJob.cancelChildren()
    }
}


abstract class BaseViewModelHandlerException(
    private val handler: (CoroutineContext, Throwable) -> Unit = { c, t -> }
) :
    ViewModel() {

    private val viewModelSupervisorJob = SupervisorJob()

    private val mutableStateCoroutineException = MutableStateFlow<Throwable?>(null)

    val stateCoroutineException = mutableStateCoroutineException.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { c, t ->
        handler(c, t)
        mutableStateCoroutineException.value = t
    }

    private val scope = CoroutineScope(Dispatchers.Main + Job() + exceptionHandler)

    private val scopeWithSupervisedJob = CoroutineScope(Dispatchers.Main + SupervisorJob() + exceptionHandler)

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
        scopeWithSupervisedJob.coroutineContext.cancelChildren()
    }

}

/*
    Saved State module for ViewModel
    https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate
 */
