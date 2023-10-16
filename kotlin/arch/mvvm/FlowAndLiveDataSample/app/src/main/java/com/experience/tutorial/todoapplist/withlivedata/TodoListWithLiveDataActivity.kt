package com.experience.tutorial.todoapplist.withlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.experience.tutorial.R

/**
 * https://github.com/android/architecture-samples/tree/livedata
 */

class TodoListWithLiveDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list_with_live_data)
    }
}