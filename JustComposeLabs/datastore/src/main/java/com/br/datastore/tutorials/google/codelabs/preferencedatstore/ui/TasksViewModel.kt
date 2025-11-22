package com.br.datastore.tutorials.google.codelabs.preferencedatstore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.br.datastore.tutorials.google.codelabs.preferencedatstore.data.SortOrder
import com.br.datastore.tutorials.google.codelabs.preferencedatstore.data.Task
import com.br.datastore.tutorials.google.codelabs.preferencedatstore.data.TasksRepository
import com.br.datastore.tutorials.google.codelabs.preferencedatstore.data.UserDataStoreRepository
import com.br.datastore.tutorials.google.codelabs.preferencedatstore.data.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine


data class TaskUiModel(
    val tasks: List<Task>,
    val showCompleted: Boolean,
    val sortOrder: SortOrder,
)


/*
    https://developer.android.com/codelabs/android-preferences-datastore#2
    Responsavel pela logica de UI
 */
class TasksSharedPreferenceViewModel(
    repository: TasksRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {

    /*
        Armazena o ultimo valor de showCompleted
     */
    private val showCompletedFlow = MutableStateFlow(false)

    /*
        Armazena o ultimo valor de sortOrder
     */
    private val sorOrderStateFlow = userPreferencesRepository.stateFlowSortOrder


    private val taskUiModelFlow = combine(
        repository.tasks,
        showCompletedFlow,
        sorOrderStateFlow
    ) { tasks: List<Task>, showCompleted: Boolean, sortOrder: SortOrder ->
    }

}


class TasksSharedPreferenceViewModelFactory(
    private val tasksRepository: TasksRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TasksSharedPreferenceViewModel::class.java)) {
             TasksSharedPreferenceViewModel(tasksRepository, userPreferencesRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}


class TasksDataStoreViewModel(
    repository: TasksRepository,
    private val userDataStoreRepository: UserDataStoreRepository
) : ViewModel() {
}

class TasksDataStoreViewModelFactory(
    private val tasksRepository: TasksRepository,
    private val userDataStoreRepository: UserDataStoreRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TasksDataStoreViewModel::class.java)) {
            TasksDataStoreViewModel(tasksRepository, userDataStoreRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}


