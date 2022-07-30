package com.br.experience.funmobdatascience.features.portfolio.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.experience.funmobdatascience.databinding.ActivityPortfolioBinding

import com.br.experience.funmobdatascience.features.sharedetails.views.ShareDetailsActivity
import com.br.experience.funmobdatascience.features.share.models.Share
import com.br.experience.funmobdatascience.features.share.views.list.adapter.InvestmentAssetBinderAdapter
import com.br.experience.funmobdatascience.views.list.action.ActionItemViewHolder
import com.br.experience.funmobdatascience.views.list.adapter.GeneralRecyclerViewAdapter

class PortfolioActivity : AppCompatActivity(), ActionItemViewHolder<Share> {

    private val binding: ActivityPortfolioBinding by lazy { ActivityPortfolioBinding.inflate(layoutInflater) }
    private val portfolioAssets: MutableList<Share> = mutableListOf()



    private val binder = InvestmentAssetBinderAdapter(this)
    private val adapter: GeneralRecyclerViewAdapter<Share> = GeneralRecyclerViewAdapter(portfolioAssets, binder)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.layoutPortfolio.rcShares.adapter
    }

    override fun onInteract(data: Share) {
        with(Intent(this, ShareDetailsActivity::class.java)) {
            putExtra(ShareDetailsActivity.KEY_INVESTMENT_ASSET, data)
            startActivity(this)
        }
    }
}