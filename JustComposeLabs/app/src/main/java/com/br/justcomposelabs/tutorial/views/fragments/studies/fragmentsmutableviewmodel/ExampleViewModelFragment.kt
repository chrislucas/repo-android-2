package com.br.justcomposelabs.tutorial.views.fragments.studies.fragmentsmutableviewmodel

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.br.justcomposelabs.R
import com.br.justcomposelabs.databinding.FragmentExampleViewModelBinding
import kotlinx.coroutines.launch

/*
    Pesquisa
    MutableStateFlow + ViewModel and viewLifecycleOwner.lifecycleScope
 */

data class MyUiState(
    val message: String = "Hello from ViewModel!",
    val counter: Int = 0
)


class ExampleViewModelFragment : Fragment() {

    private var mBinding: FragmentExampleViewModelBinding? = null
    private val binding get() = mBinding!!


    companion object {
        fun newInstance() = ExampleViewModelFragment()
    }

    private val viewModel: SimpleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentExampleViewModelBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                binding.tvMessage.text = uiState.message
            }
        }

        binding.incrementButton.setOnClickListener {
            viewModel.incrementCounter()
        }

        binding.updateMessageButton.setOnClickListener {
            viewModel.updateMessage("Message updated!")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}