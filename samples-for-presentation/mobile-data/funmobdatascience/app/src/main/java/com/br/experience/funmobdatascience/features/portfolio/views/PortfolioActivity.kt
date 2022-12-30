package com.br.experience.funmobdatascience.features.portfolio.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.br.experience.funmobdatascience.databinding.ActivityPortfolioBinding
import com.br.experience.funmobdatascience.features.portfolio.models.PortfolioAsset
import com.br.experience.funmobdatascience.features.portfolio.viewmodel.PortfolioViewModel
import com.br.experience.funmobdatascience.features.portfolio.views.list.BuildPortfolioAssetViewHolder
import com.br.experience.funmobdatascience.features.portfolio.views.list.ProvidePortfolioAssetViewType
import com.br.experience.funmobdatascience.utils.viewmodel.MapperViewModelFactory
import com.br.experience.funmobdatascience.utils.viewmodel.ProviderViewModel.createViewModel
import com.br.experience.funmobdatascience.views.list.action.ActionItemViewHolder
import com.br.experience.funmobdatascience.views.list.action.GenericBinderAdapterToViewHolder
import com.br.experience.funmobdatascience.views.list.adapter.GeneralRecyclerViewAdapter

class PortfolioActivity : AppCompatActivity(), ActionItemViewHolder<PortfolioAsset> {

    private val binding: ActivityPortfolioBinding by lazy { ActivityPortfolioBinding.inflate(layoutInflater) }
    private val portfolioAssets: MutableList<PortfolioAsset> = mutableListOf()

    private val binder = GenericBinderAdapterToViewHolder(
        this,
        ProvidePortfolioAssetViewType(),
        BuildPortfolioAssetViewHolder()
    )
    private val adapter: GeneralRecyclerViewAdapter<PortfolioAsset> = GeneralRecyclerViewAdapter(portfolioAssets, binder)

    private val viewModel: PortfolioViewModel by lazy { createViewModel(PortfolioViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.layoutPortfolio.rcShares.adapter = adapter
        initViewModel()
    }

    override fun onInteract(data: PortfolioAsset) {
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