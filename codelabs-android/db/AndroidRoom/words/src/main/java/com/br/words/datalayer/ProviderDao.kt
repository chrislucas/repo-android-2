package com.br.words.datalayer

import android.content.Context
import com.br.words.datalayer.daos.WordDao
import com.br.words.datalayer.local.WordRoomDatabase


fun provideWordDao(context: Context): WordDao =
    WordRoomDatabase.getInstance(context).getWordDaoInstance()