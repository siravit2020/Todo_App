package com.example.todomvvm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Insert
    suspend fun insertTodo(todo:Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("SELECT * FROM todo_table ORDER BY todo_id ASC")
    fun getTodos() : LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table ORDER BY todo_id DESC")
    fun getTodos2() : LiveData<List<Todo>>
}