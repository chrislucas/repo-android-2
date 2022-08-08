package com.br.experience.funmobdatascience.features.shares.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.br.experience.funmobdatascience.databinding.ActivitySharesBinding
import com.br.experience.funmobdatascience.features.portfolio.views.PortfolioActivity
import com.br.experience.funmobdatascience.features.shares.models.Share
import com.br.experience.funmobdatascience.features.shares.viewmodel.SharesViewModel
import com.br.experience.funmobdatascience.features.shares.views.list.adapter.InvestmentAssetBinderAdapter
import com.br.experience.funmobdatascience.features.shareprojection.views.ShareDetailsActivity
import com.br.experience.funmobdatascience.utils.viewmodel.MapperViewModelFactory
import com.br.experience.funmobdatascience.utils.viewmodel.ProviderViewModel.createViewModel
import com.br.experience.funmobdatascience.views.list.action.ActionItemViewHolder
import com.br.experience.funmobdatascience.views.list.adapter.GeneralRecyclerViewAdapter

class SharesActivity : AppCompatActivity(), ActionItemViewHolder<Share> {

    private val binding: ActivitySharesBinding by lazy { ActivitySharesBinding.inflate(layoutInflater) }

    private val assetsAvailable: MutableList<Share> = mutableListOf()

    private val binder = InvestmentAssetBinderAdapter(this)
    private val adapter: GeneralRecyclerViewAdapter<Share> = GeneralRecyclerViewAdapter(assetsAvailable, binder)

    private val factoryViewModel: MapperViewModelFactory by lazy { MapperViewModelFactory() }

    private val viewModel: SharesViewModel by lazy {
        createViewModel(factoryViewModel, SharesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.layoutShares.rcShares.adapter = adapter
        binding.layoutShares.cvWrapperQuantityInvested.setOnClickListener {
            startActivity(Intent(this, PortfolioActivity::class.java))
        }
        searchAssets()
    }

    override fun onInteract(data: Share) {
        with(Intent(this, ShareDetailsActivity::class.java)) {
            putExtra(ShareDetailsActivity.KEY_INVESTMENT_ASSET, data)
            startActivity(this)
        }
    }

    private fun searchAssets() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                startObserverViewModel()
            }
        }
    }

    private suspend fun startObserverViewModel() {
        viewModel.observableSateFindInvAssets.collect { operation ->
            operation.data?.let {
                adapter.updateCollection(it.toMutableList())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.findAssets()
    }
}