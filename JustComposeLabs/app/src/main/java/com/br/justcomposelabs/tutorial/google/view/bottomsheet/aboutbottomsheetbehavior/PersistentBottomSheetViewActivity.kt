package com.br.justcomposelabs.tutorial.google.view.bottomsheet.aboutbottomsheetbehavior

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.justcomposelabs.R
import com.br.justcomposelabs.databinding.PersistentActivityBottomSheetViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

/*
    https://medium.com/swlh/bottomsheetdialogfragment-made-simpler-b32fa8e20928
    https://droidbyme.medium.com/android-bottom-sheet-7e9cfcec6427

    - O que é BottomSheet modal ?
        - A ideia é colocar o usuario num "modo" diferente e, o usuárop pde trocar
        para o "modo" anterior. Em android usa-se Dialogs como base para as modais

    - O que é a Persistent BottomSheet ?
        -

    Difference Between Modal and Persistent Bottom Sheet in Android
    https://www.geeksforgeeks.org/android/difference-between-modal-and-persistent-bottom-sheet-in-android/

    com.google.android.material.bottomsheet
    https://developer.android.com/reference/com/google/android/material/bottomsheet/package-summary

 */
class PersistentBottomSheetViewActivity : AppCompatActivity() {

    private val binding: PersistentActivityBottomSheetViewBinding by lazy {
        PersistentActivityBottomSheetViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }


            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            toolbar.setNavigationOnClickListener { }

            val sheetBehavior = BottomSheetBehavior.from(simpleBottomSheetDialogLayout.bottomSheet)
            mainContentBottomSheet.btnBottomSheet.setOnClickListener {
                if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {

                } else {

                }
            }

            sheetBehavior.addBottomSheetCallback(
                object : BottomSheetBehavior.BottomSheetCallback() {
                    /*
                        https://developer.android.com/reference/com/google/android/material/bottomsheet/BottomSheetBehavior.BottomSheetCallback
                     */
                    override fun onStateChanged(bottomSheet: View, state: Int) {
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    }

                })
        }

    }
}


