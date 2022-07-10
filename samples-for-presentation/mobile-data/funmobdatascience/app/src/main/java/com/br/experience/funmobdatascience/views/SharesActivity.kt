package com.br.experience.funmobdatascience.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.experience.funmobdatascience.databinding.ActivitySharesBinding
import com.br.experience.funmobdatascience.models.Share
import com.br.experience.funmobdatascience.views.list.InvestmentAssetBinderAdapter
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