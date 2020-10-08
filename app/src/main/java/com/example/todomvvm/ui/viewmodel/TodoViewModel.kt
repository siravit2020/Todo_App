package com.example.todomvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todomvvm.data.Todo
import com.example.todomvvm.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository): ViewModel() {

    val todoList:LiveData<List<Todo>> = repository.todos
    fun insert(todo:Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todo)
            Log.d("item","insert")
        }
    }
    fun delete(todo:Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(todo)
        }
    }
    fun update(todo:Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(todo)
        }
    }
}