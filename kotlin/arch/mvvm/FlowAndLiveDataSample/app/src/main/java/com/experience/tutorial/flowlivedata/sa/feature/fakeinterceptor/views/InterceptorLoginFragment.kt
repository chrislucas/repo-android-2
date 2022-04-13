package com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.experience.tutorial.R

/**
 * Fragment que conté uma lista de escolha de qual interceptor de request http
 * o usuario pode escolher para usar esse app de exemplo
 *
 * TODO usar o componente de viewmodel para capturar a escolha do item do spinner
 *
 */
class InterceptorLoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interceptor_login, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            InterceptorLoginFragment()
    }
}