package com.br.experience.funmobdatascience.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.experience.funmobdatascience.databinding.ActivityListAssetsBinding
import com.br.experience.funmobdatascience.models.InvestmentAsset
import com.br.experience.funmobdatascience.views.list.InvestmentAssetBinderAdapter
import com.br.experience.funmobdatascience.views.list.action.ActionItemViewHolder
import com.br.experience.funmobdatascience.views.list.adapter.GeneralRecyclerViewAdapter

class ListAssetsActivity : AppCompatActivity(), ActionItemViewHolder<InvestmentAsset> {

    private val binding: ActivityListAssetsBinding by lazy { ActivityListAssetsBinding.inflate(layoutInflater) }

    private val assetsAvailable: MutableList<InvestmentAsset> = mutableListOf()

    private val binder = InvestmentAssetBinderAdapter(this)
    private val adapter: GeneralRecyclerViewAdapter<InvestmentAsset> = GeneralRecyclerViewAdapter(assetsAvailable, binder)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rcAssetsForSell.adapter = adapter
    }

    override fun onInteract(data: InvestmentAsset) {
        val intent = Intent(this, AssetDetailsActivity::class.java)
        startActivity(intent)
    }
}