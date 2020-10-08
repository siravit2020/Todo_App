package com.example.todomvvm.ui

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.example.todomvvm.R
import com.example.todomvvm.data.Todo
import com.example.todomvvm.databinding.ActivityAddTodoBinding
import java.util.*

class AddTodoActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_todo)
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.topAppBar.setOnMenuItemClickListener {
            if(it.itemId == R.id.folder){
                saveTodo()
            }
            true
        }
        binding.timeEditText.setOnClickListener {
            openTimePicker()
        }
    }

    private fun saveTodo(){
        val title = binding.titleEditText.text.toString()
        val time = binding.timeEditText.text.toString()
        val todo = Todo(0,title,time,false)
        val bundle:Bundle = bundleOf("bundle" to todo)
        val intent = Intent()
        intent.putExtra("key",bundle)
        setResult(1,intent)
        finish()
    }

    fun openTimePicker(){
        val calendar=Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val msg = checkTime(hourOfDay,minute)
            binding.timeEditText.setText(msg)
        }
        val dialog = TimePickerDialog(this,timeListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true)
        dialog.show()
    }

    private fun checkTime(hourOfDay: Int, minute: Int): String {
        var msg=""
        if(hourOfDay < 10 && minute < 10){
            msg = "0$hourOfDay:0$minute"
        }
        else if(hourOfDay < 10 && minute > 10){
            msg = "0$hourOfDay:$minute"
        }
        else if(hourOfDay > 10 && minute < 10){
            msg = "$hourOfDay:0$minute"
        }
        else if(hourOfDay > 10 && minute > 10){
            msg = "$hourOfDay:$minute"
        }
        return msg
    }
}