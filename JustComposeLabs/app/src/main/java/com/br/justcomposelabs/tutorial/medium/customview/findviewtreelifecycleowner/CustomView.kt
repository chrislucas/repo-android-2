package com.br.justcomposelabs.tutorial.medium.customview.findviewtreelifecycleowner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.getValue


/*
    https://medium.com/@nicholas.rose/level-up-your-custom-views-d0c6f69fd1ec

    https://gist.github.com/Guang1234567/3be582daab6bb5a5a49b9d6f86a89b0e


    View.findViewTreeLifecycleOwner()
    View.findViewTreeViewModelStoreOwner()
    View.findViewTreeSavedStateRegistryOwner()
 */

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val viewModel: CustomViewModel? by lazy {
        findViewTreeViewModelStoreOwner()?.let { lifecycleOwner ->
            ViewModelProvider(lifecycleOwner)[CustomViewModel::class.java]
        }
    }
}


class CustomViewModel() : ViewModel()