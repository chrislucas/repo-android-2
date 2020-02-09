package br.xplorer.driwm

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog

open class BaseActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    fun showSafelyAlertDialog(alertDialog: AlertDialog?) {
        runOnUiThread {
            if(!isFinishing) {
                alertDialog?.show()
            }
        }
    }


    fun dismissSafelyAlertDialog(alertDialog: AlertDialog?) {
        runOnUiThread {
            if(!isFinishing && alertDialog?.isShowing != false) {
                alertDialog?.dismiss()
            }
        }
    }

}
