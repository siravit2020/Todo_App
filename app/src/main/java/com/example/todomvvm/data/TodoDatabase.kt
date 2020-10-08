package com.example.todomvvm.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Todo::class],version = 1)
abstract class TodoDatabase:RoomDatabase() {

    abstract fun todoDao():TodoDao

    companion object{
        @Volatile
        private var INSTANCE:TodoDatabase? = null
        fun getInstance(context:Context):TodoDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,TodoDatabase::class.java,"todo_db").build()
                    Log.d("ghj","เข้ามาทำ")
                }
                return instance
            }

        }
    }
}