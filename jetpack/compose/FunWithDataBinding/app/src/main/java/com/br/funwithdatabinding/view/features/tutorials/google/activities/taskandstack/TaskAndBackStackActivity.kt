package com.br.funwithdatabinding.view.features.tutorials.google.activities.taskandstack

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO estudar
    https://developer.android.com/guide/components/activities/tasks-and-back-stack

    Manage tasks
    https://developer.android.com/guide/components/activities/tasks-and-back-stack#ManagingTasks

    Is there any way to have one and only one instance of each activity?
    https://stackoverflow.com/questions/8995987/is-there-any-way-to-have-one-and-only-one-instance-of-each-activity

    Manifest <activity> attrs
    https://developer.android.com/guide/topics/manifest/activity-element.html
 */
class TaskAndBackStackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task_and_back_stack)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}