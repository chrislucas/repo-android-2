package com.br.debuggingcompose

import android.app.Application
import androidx.compose.runtime.Composer
import androidx.compose.runtime.tooling.ComposeStackTraceMode

/*
    https://developer.android.com/develop/ui/compose/tooling/stacktraces
 */

class CustomApplication(): Application() {

    override fun onCreate() {
        super.onCreate()
        // Habilita stack trace para minified builds only
        Composer.setDiagnosticStackTraceMode(ComposeStackTraceMode.Auto)

        // alternativamente: Habilita verbose Compose strack trace para debugging loca
        Composer.setDiagnosticStackTraceMode(ComposeStackTraceMode.SourceInformation)
    }
}