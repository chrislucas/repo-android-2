package com.br.experience.funmobdatascience.features.share.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.experience.funmobdatascience.databinding.ActivitySharesBinding
import com.br.experience.funmobdatascience.features.share.models.Share
import com.br.experience.funmobdatascience.features.portfolio.views.PortfolioActivity
import com.br.experience.funmobdatascience.features.sharedetails.views.ShareDetailsActivity
import com.br.experience.funmobdatascience.features.share.views.list.adapter.InvestmentAssetBinderAdapter
import com.br.experience.funmobdatascience.views.list.action.ActionItemViewHolder
import com.br.experience.funmobdatascience.views.list.adapter.GeneralRecyclerViewAdapter

class SharesActivity : AppCompatActivity(), ActionItemViewHolder<Share> {

    private val binding: ActivitySharesBinding by lazy { ActivitySharesBinding.inflate(layoutInflater) }

    private val assetsAvailable: MutableList<Share> = mutableListOf()

    private val binder = InvestmentAssetBinderAdapter(this)
    private val adapter: GeneralRecyclerViewAdapter<Share> = GeneralRecyclerViewAdapter(assetsAvailable, binder)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.layoutShares.rcShares.adapter = adapter

        binding.layoutShares.cvWrapperQuantityInvested.setOnClickListener {
            startActivity(Intent(this, PortfolioActivity::class.java))
        }
    }

    override fun onInteract(data: Share) {
        with(Intent(this, ShareDetailsActivity::class.java)) {
            putExtra(ShareDetailsActivity.KEY_INVESTMENT_ASSET, data)
            startActivity(this)
        }
    }
}