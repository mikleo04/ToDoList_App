package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val task_id = intent.getIntExtra(TASK_ID, 0)

        val taskViewModel = ViewModelProvider(this, ViewModelFactory
            .getInstance(this))
            .get(DetailTaskViewModel::class.java)
        taskViewModel.setTaskId(task_id)

        taskViewModel.task.observe(this){
            if (it != null){
                findViewById<EditText>(R.id.detail_ed_title).setText(it.title)
                findViewById<EditText>(R.id.detail_ed_description).setText(it.description)
                findViewById<EditText>(R.id.detail_ed_due_date).setText(DateConverter.convertMillisToString(it.dueDateMillis))
            }
        }
        findViewById<Button>(R.id.btn_delete_task).setOnClickListener {
            taskViewModel.deleteTask()
            finish()
        }

    }
}