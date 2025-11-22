package com.br.datastore.tutorials.google.codelabs.preferencedatstore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/*
    when to use enum class instead of sealed class?

    Use enum class when:

    - You need a fixed, finite set of distinct constants. Enums are ideal for representing
    categories or types that have a limited and predefined set of values,
    such as days of the week, colors, or compass directions.

    - The values primarily serve as simple identifiers.
     While enums can have properties and methods, their core purpose is to represent distinct, unchanging constants.

    - You need to easily iterate over all possible values.
    Enum classes provide built-in functions like values() to retrieve all defined enum entries.

    Use sealed class when:

    - You need a type that can have a limited, but potentially more complex,
      set of forms or states.
      Sealed classes allow you to define a restricted class hierarchy
      where all direct subclasses are known at compile time.

    - Each form or state might require its own unique data or behavior.
    Unlike enums,
     sealed classes allow subclasses to be data classes or objects with their own distinct properties and methods.

    - You require exhaustive checking in when expressions.
     The compiler ensures that all possible subclasses of a sealed class are handled in a when statement,
     preventing potential runtime errors.


*/
enum class SortOrder {
    None,
    BY_DEADLINE,
    BY_PRIORITY,
    BY_DEADLINE_AND_PRIORITY
}

private const val USER_PREFERENCES_NAME = "user_preferences"
private const val SORT_ORDER_KEY = "sort_order"


interface UserRepository {

}
/*
    Classe que mantem as preferencias do usuário.
 */
class UserPreferencesRepository private constructor(context: Context) {
    private val sharedPreference = context.applicationContext.getSharedPreferences(
        USER_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )


    private val sortOrder: SortOrder
        get() {
            val order = sharedPreference.getString(SORT_ORDER_KEY, SortOrder.None.name)
            return SortOrder.valueOf(order ?: SortOrder.None.name)
        }

    private val mutableStateFlowSortOrder = MutableStateFlow(sortOrder)

    val stateFlowSortOrder: StateFlow<SortOrder> = mutableStateFlowSortOrder
}

/*
    https://github.com/android/codelab-android-datastore/blob/preferences_datastore/app/src/main/java/com/codelab/android/datastore/data/UserPreferencesRepository.kt
 */
class UserDataStoreRepository (private val dataStore: DataStore<Preferences>) {

}