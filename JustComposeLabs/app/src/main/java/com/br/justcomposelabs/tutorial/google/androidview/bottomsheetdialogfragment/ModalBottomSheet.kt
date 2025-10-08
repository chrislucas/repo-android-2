package com.br.justcomposelabs.tutorial.google.androidview.bottomsheetdialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/*
    Classes do pacote bottom sheet
    https://developer.android.com/reference/com/google/android/material/bottomsheet/package-summary

    Tutorial
    https://m2.material.io/components/sheets-bottom/android#using-bottom-sheets
 */
class ModalBottomSheet: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}