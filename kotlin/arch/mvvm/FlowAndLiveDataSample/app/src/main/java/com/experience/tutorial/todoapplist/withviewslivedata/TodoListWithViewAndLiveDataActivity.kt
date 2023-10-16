package com.experience.tutorial.todoapplist.withviewslivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.experience.tutorial.R

/**
 * Principal projeto
 * https://github.com/android/architecture-samples
 *
 * https://github.com/android/architecture-samples/tree/views
 * https://github.com/android/architecture-samples/wiki/To-do-app-specification
 */
class TodoListWithViewAndLiveDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list_with_view_and_live_data)
    }
}