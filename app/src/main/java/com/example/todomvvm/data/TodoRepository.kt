package com.example.todomvvm.data

class TodoRepository (private val dao : TodoDao){

    val todos = dao.getTodos2()
    suspend fun insert(todo: Todo){
        return dao.insertTodo(todo)
    }
    suspend fun delete(todo: Todo) {
        return dao.deleteTodo(todo)
    }
    suspend fun update(todo: Todo){
        return dao.updateTodo(todo)
    }
}