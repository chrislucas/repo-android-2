package com.xp.samplemvvmarch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.xp.samplemvvmarch.viewmodel.UserProfileFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.wrapper_layout_fragment
                , UserProfileFragment.newInstance()
                , UserProfileFragment::class.java.name)
            .commit()

    }
}
