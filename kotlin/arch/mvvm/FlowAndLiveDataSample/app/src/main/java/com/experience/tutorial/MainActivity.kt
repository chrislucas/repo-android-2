package com.experience.tutorial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.experience.tutorial.databinding.ActivityMainBinding
import com.experience.tutorial.flowlivedata.sa.feature.withflow.views.activitiies.LoginWithFlowActivity
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.views.activities.LoginWithLiveDataActivity

class MainActivity : AppCompatActivity() {

    private val bindView: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView.root)
    }

    override fun onResume() {
        super.onResume()
        bindView.btnLoginWithLivedata.setOnClickListener {
            startActivity(Intent(this, LoginWithLiveDataActivity::class.java))
        }
        bindView.btnLoginWithFlpw.setOnClickListener {
            startActivity(Intent(this, LoginWithFlowActivity::class.java))
        }
    }
}