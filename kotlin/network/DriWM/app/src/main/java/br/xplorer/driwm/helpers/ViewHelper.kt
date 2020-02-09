package br.xplorer.driwm.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import br.xplorer.driwm.R

object ViewHelper {


    data class AlertDialogConfig(val title: String = "", val message: String = ""
                                  , val isCancelable: Boolean = true)

    interface DelegateConfigAlertDialog {
        fun config(view: View, alertDialogConfig: AlertDialogConfig)
    }


    @JvmStatic
    fun buildProgressBar(context: Context, @LayoutRes layout : Int = R.layout.layout_default_progress_dialog
                         , alertDialogConfig: AlertDialogConfig = AlertDialogConfig()
                         , delegate: DelegateConfigAlertDialog? = null
    ) : AlertDialog.Builder {
        val builder = AlertDialog.Builder(context)

        if (delegate == null) {
            builder.setView(layout)
        }
        else {
            val view = LayoutInflater.from(context).inflate(layout, null, false)
            delegate.config(view, alertDialogConfig)
            builder.setView(view)
        }

        return builder
    }

}