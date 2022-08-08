package com.br.experience.funmobdatascience.features.portfolio.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.br.experience.funmobdatascience.databinding.ActivityPortfolioBinding
import com.br.experience.funmobdatascience.features.portfolio.models.ShareDetails
import com.br.experience.funmobdatascience.features.portfolio.viewmodel.PortfolioViewModel
import com.br.experience.funmobdatascience.features.shares.views.list.adapter.PortfolioBinderAdapter
import com.br.experience.funmobdatascience.utils.viewmodel.MapperViewModelFactory
import com.br.experience.funmobdatascience.utils.viewmodel.ProviderViewModel.createViewModel
import com.br.experience.funmobdatascience.views.list.action.ActionItemViewHolder
import com.br.experience.funmobdatascience.views.list.adapter.GeneralRecyclerViewAdapter

class PortfolioActivity : AppCompatActivity(), ActionItemViewHolder<ShareDetails> {

    private val binding: ActivityPortfolioBinding by lazy { ActivityPortfolioBinding.inflate(layoutInflater) }
    private val portfolioAssets: MutableList<ShareDetails> = mutableListOf()

    private val binder = PortfolioBinderAdapter(this)
    private val adapter: GeneralRecyclerViewAdapter<ShareDetails> = GeneralRecyclerViewAdapter(portfolioAssets, binder)
    private val factoryViewModel: MapperViewModelFactory by lazy { MapperViewModelFactory() }

    private val viewModel: PortfolioViewModel by lazy {
        createViewModel(factoryViewModel, PortfolioViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.layoutPortfolio.rcShares.adapter = adapter
        initViewModel()
    }

    override fun onInteract(data: ShareDetails) {
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observableStateGetPortfolio.collect { operation ->
                    operation.data?.shares?.let { shared ->
                        adapter.updateCollection(shared.toMutableList())
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPortfolio("321", "port_1")
    }
}