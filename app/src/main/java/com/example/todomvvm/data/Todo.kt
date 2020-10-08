package com.example.todomvvm.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = "todo_table")
data class Todo(@PrimaryKey(autoGenerate = true)
                @ColumnInfo(name = "todo_id")
                val id:Int,
                val title:String,
                val time:String,
                val isComplete:Boolean) : Parcelable