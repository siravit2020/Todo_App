package com.example.todomvvm.untils

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todomvvm.R
import com.example.todomvvm.data.Todo
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoRecyclerAdapter(private val onCheck: onCheckListener) : RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder>() {
    var todoList = listOf<Todo>()
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title = itemView.title
        private val time = itemView.time
        private val checkBox = itemView.checkBox

        fun bind(todo: Todo,action:onCheckListener){
            title.text = todo.title
            time.text = todo.time
            checkBox.isChecked = todo.isComplete
            if(checkBox.isChecked){
                title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            else title.paintFlags = 0
            checkBox.setOnClickListener {
                if(checkBox.isChecked){
                    action.onCheckBox(adapterPosition,true)
                }else{
                    action.onCheckBox(adapterPosition,false)
                }
            }

        }
    }

    interface onCheckListener{
        fun onCheckBox(position: Int,state:Boolean)
    }

    fun submitList(list:List<Todo>){
        this.todoList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = todoList[position]
        holder.bind(current,onCheck)
    }

    override fun getItemCount(): Int
        = todoList.size

}