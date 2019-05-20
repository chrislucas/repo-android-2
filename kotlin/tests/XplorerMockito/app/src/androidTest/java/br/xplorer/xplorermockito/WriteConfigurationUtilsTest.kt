package br.xplorer.xplorermockito

import android.content.Context

import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt


import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

import java.io.FileOutputStream

class WriteConfigurationUtilsTest {


    @Rule
    val rule = MockitoJUnit.rule()

    @Mock
    lateinit var context : Context

    @Mock
    lateinit var fileOutputStream : FileOutputStream

    @Test fun writeShouldWriteTwiceToFileSystem() {
        Mockito.`when`(context.openFileOutput(any(), anyInt()))
            .thenReturn(fileOutputStream)
    }
}