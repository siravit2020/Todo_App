package com.example.todomvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todomvvm.data.TodoRepository

@Suppress("UNCHECKED_CAST")
class TodoViewModelFactory(val repository: TodoRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)){
            return TodoViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown View Model class")
    }
}