package com.example.feature.intnavigation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.TemplateProviderViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.adapter.GenericRecyclerViewAdapter
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderData
import com.example.androidallcodelabs.databinding.ActivityIntentNavigationBinding
import com.example.feature.intnavigation.models.providerDeepLinks
import com.example.feature.intnavigation.view.model.DeepLinkDispatcher
import com.example.feature.intnavigation.view.rc.action.DeeplinkViewHolderBuilder

class IntentNavigationActivity : AppCompatActivity() {

    private val bind: ActivityIntentNavigationBinding by lazy {
        ActivityIntentNavigationBinding.inflate(layoutInflater)
    }

    private val items: List<ViewHolderData<DeepLinkDispatcher>> by lazy {
        providerDeepLinks(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        with(bind.deepLinkList) {
            adapter = GenericRecyclerViewAdapter(
                emptyList<ViewHolderData<DeepLinkDispatcher>>(),
                TemplateProviderViewHolder(DeeplinkViewHolderBuilder())
            )
        }
    }
}