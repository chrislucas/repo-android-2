package com.example.usingcomposecompilergradleplugin.view.features.concatadapters

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.usingcomposecompilergradleplugin.R


/**
 * Sobre Concat Adapter
 * https://medium.com/androiddevelopers/merge-adapters-sequentially-with-mergeadapter-294d2942127a
 *
 * ConcatAdapter
 * https://developer.android.com/reference/androidx/recyclerview/widget/ConcatAdapter?authuser=1
 *
 * Todo criar um exemplo de codigo para cada link abaixo relacionado ao ConcatAdapter
 * https://proandroiddev.com/concatadapter-deep-dive-9f447eb1cbef
 *
 * Beginner’s Guide on ConcatAdapter in Android
 * https://medium.com/codechai/beginners-guide-on-concatadapter-in-android-b95f1e3d2b1e
 * https://www.geeksforgeeks.org/concatadapter-in-android/
 * https://github.com/akexorcist/ConcatAdapterMultipleLayoutManager
 *
 * How to use the RecyclerView selection library with ConcatAdapter?
 * https://stackoverflow.com/questions/74617234/how-to-use-the-recyclerview-selection-library-with-concatadapter
 *
 * Beginner's Guide on ConcatAdapter in Android
 *  https://wajahatkarim.com/2022/06/concat-adapter/
 */
class FunWithConcatAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fun_with_concat_adapter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}