package com.br.bottomsheetsample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior


/**
 * https://proandroiddev.com/android-bottom-sheet-behavior-and-animated-button-on-top-of-it-da86a9bfe545
 * https://medium.com/android-dev-br/android-ui-bottom-sheet-4709cad826d2
 * https://developer.android.com/reference/com/google/android/material/bottomsheet/BottomSheetBehavior
 *
 * BottomSheetBehavior states
 *
 * 1) STATE_COLLAPSED: O componente fica visivel e sua altura de definida pelo atributo peek_height.
 * Esse é usualmente o estado de 'repouso' do componente
 *
 * 2) STATE_EXPANDED: O componente e totalmente visivel com sua altura no maximo. Ele deixxa
 * de ser "arrastável (dragging)"
 *
 * 3) STATE_DRAGGING: O usuario arrastou o componente para cima ou para baixo
 *
 * 4) STATE_SETTLING: O componente esta em repouso numa altura especifica apos um evento de drag/swipe.
 * A altura pode ser a definida pelo atributo peekHeight, expandedHeight ou 0 caso a acao do usuario
 * esconda o componente
 *
 * 5) STATE_HIDDEN:
 *
 *
 *
 * */
class SampleBottomSheetOnLayout : Fragment() {


    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            val state = when (newState) {
                BottomSheetBehavior.STATE_COLLAPSED -> {
                    "STATE_COLLAPSED"
                }
                BottomSheetBehavior.STATE_EXPANDED -> {
                    "STATE_EXPANDED"
                }

                BottomSheetBehavior.STATE_DRAGGING -> {
                    "STATE_DRAGGING"
                }
                BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    "STATE_HALF_EXPANDED"
                }

                BottomSheetBehavior.STATE_HIDDEN -> {
                    "STATE_HIDDEN"
                }
                BottomSheetBehavior.STATE_SETTLING -> {
                    "STATE_SETTLING"
                }
                else -> {
                    "UNDEFINED"
                }
            }

            Log.i("STATE_BOTTOM_SHEET", state)
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            Log.i("ON_SLIDE_BOTTOM_SHEET", "Deslocamento: $slideOffset")
        }
    }

    var bottomSheetView: LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =
            inflater.inflate(
                R.layout.fragment_sample_bottom_sheet_on_layout, container,
                false
            )
        bottomSheetView = view.findViewById(R.id.linear_bottom_sheet)



        bottomSheetView?.let {
            BottomSheetBehavior.from(it).addBottomSheetCallback(bottomSheetCallback)
        }



        return view
    }

    override fun onDestroy() {
        super.onDestroy()

        bottomSheetView?.let {
            BottomSheetBehavior.from(it).removeBottomSheetCallback(bottomSheetCallback)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SampleBottomSheetOnLayout()
    }
}