package com.br.experience.funmobdatascience.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.experience.funmobdatascience.R
import com.br.experience.funmobdatascience.databinding.ActivityAssetDetailsBinding
import com.br.experience.funmobdatascience.models.InvestmentAsset

class AssetDetailsActivity : AppCompatActivity() {

    private var asset: InvestmentAsset? = null
    private val binding: ActivityAssetDetailsBinding by lazy { ActivityAssetDetailsBinding.inflate(layoutInflater) }

    companion object {
        const val KEY_INVESTMENT_ASSET = "KEY_INVESTMENT_ASSET"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset_details)

        savedInstanceState?.let {
            asset = it.getParcelable(KEY_INVESTMENT_ASSET)
        }
    }
}