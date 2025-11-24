package com.br.funwithhilt.google.studies.search.fulltextsearch

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/*
    Storing and Searching for Data
    https://developer.android.com/develop/ui/views/search/training/search
 */

class VirtualDatabase(context: Context) {
}


class VirtualDbViewModel(): ViewModel() {
    /*
        Populate the Virtual Table
        https://developer.android.com/develop/ui/views/search/training/search#populate
        https://github.com/UWT-Student-Engineers/AskMax-Android/blob/master/app/src/main/java/edu/uw/askmax/DatabaseTable.java#L153
     */

    fun loadWords() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {

            }
        }
    }
}