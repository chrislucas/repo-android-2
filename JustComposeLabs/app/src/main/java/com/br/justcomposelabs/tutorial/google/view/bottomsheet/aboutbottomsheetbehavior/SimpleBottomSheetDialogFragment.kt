package com.br.justcomposelabs.tutorial.google.view.bottomsheet.aboutbottomsheetbehavior

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.br.justcomposelabs.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber

class SimpleBottomSheetDialogFragment(
    private val lifecycleObserver: DefaultLifecycleObserver
) : BottomSheetDialogFragment() {

    /*
        Lifecycle
        onCreate()
        onCreateDialog()
        onCreateView()
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.simple_bottom_sheet_dialog,
            container, false
        )
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        /*
        parentFragment?.setFragmentResult()
        parentFragment?.setFragmentResultListener()

         */
    }

    override fun onStart() {
        super.onStart()
        lifecycle.addObserver(lifecycleObserver)
    }

    override fun onStop() {
        super.onStop()
        lifecycle.removeObserver(lifecycleObserver)
    }


}


class DefaultBottomSheetDialogFragment : DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Timber.tag("OwnLifecycleObserver").d("ON_START")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Timber.tag("OwnLifecycleObserver").d("ON_START")
    }
}
