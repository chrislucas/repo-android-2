package com.example.feature.intnavigation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.adapter.GenericRecyclerViewAdapter
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderItem
import com.example.androidallcodelabs.databinding.ActivityIntentNavigationBinding
import com.example.feature.intnavigation.models.Deeplink
import com.example.feature.intnavigation.models.providerDeepLinks
import com.example.feature.intnavigation.view.rc.action.TemplateProviderViewHolderDefault

class IntentNavigationActivity : AppCompatActivity() {

    private val bind: ActivityIntentNavigationBinding by lazy {
        ActivityIntentNavigationBinding.inflate(layoutInflater)
    }

    private val items: List<ViewHolderItem<ViewHolder, Deeplink>> by lazy {
        providerDeepLinks(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        with(bind.deepLinkList) {
            adapter = GenericRecyclerViewAdapter(
                items,
                TemplateProviderViewHolderDefault()
            )
        }
    }
}