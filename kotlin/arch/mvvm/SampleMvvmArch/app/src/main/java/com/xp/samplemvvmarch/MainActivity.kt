package com.xp.samplemvvmarch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xp.samplemvvmarch.fragments.Simple
import com.xp.samplemvvmarch.fragments.UserProfileFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = UserProfileFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.wrapper_layout_fragment
                , fragment
                , "TAG")
            .addToBackStack("TAG")
            .commit()

    }
}
