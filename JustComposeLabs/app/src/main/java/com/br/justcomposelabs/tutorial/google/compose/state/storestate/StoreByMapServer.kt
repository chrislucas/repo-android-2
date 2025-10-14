package com.br.justcomposelabs.tutorial.google.compose.state.storestate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable

/*
    https://developer.android.com/develop/ui/compose/state#mapsaver

    Se por algum motivo nao for possivel usar Parcelize, podemos tentar mapSaver para
    definir um saver customizado convertendo o objeto a ser salvo num conjunto
    de valores que o sistema pode guardar num Bundle
 */

val CitySaver = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = {
            mapOf(nameKey to it.name, countryKey to it.country)
        },
        restore = {
            City(
                it[nameKey] as String,
                it[countryKey] as String
            )
        }
    )
}


/*
    https://developer.android.com/develop/ui/compose/state#listsaver
    Se quiermos evitar a necessidade de definir chaves para o Map do MapSaver
    podemos usar listSaver e usar os indicies como chave
 */
val CityListSaver = listSaver<City, Any> (
    save = { listOf(it.name, it.country) },
    restore = { City(it[0] as String, it[1] as String) }
)

@Composable
fun CityScreenII(city: City) {

    val selectedCity = rememberSaveable(stateSaver = CitySaver) {
        mutableStateOf(city)
    }

    val selectedCityII = rememberSaveable(stateSaver = CityListSaver){
        mutableStateOf(city)
    }
}