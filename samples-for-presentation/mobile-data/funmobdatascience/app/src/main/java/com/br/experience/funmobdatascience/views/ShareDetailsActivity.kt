package com.br.experience.funmobdatascience.views

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.br.experience.funmobdatascience.databinding.ActivityShareDetailsBinding
import com.br.experience.funmobdatascience.models.Share

class ShareDetailsActivity : AppCompatActivity() {

    private var asset: Share? = null
    private val binding: ActivityShareDetailsBinding by lazy { ActivityShareDetailsBinding.inflate(layoutInflater) }

    companion object {
        const val KEY_INVESTMENT_ASSET = "KEY_INVESTMENT_ASSET"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        savedInstanceState?.let {
            asset = it.getParcelable(KEY_INVESTMENT_ASSET)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable(KEY_INVESTMENT_ASSET, asset)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        asset = savedInstanceState.getParcelable(KEY_INVESTMENT_ASSET)
    }
}