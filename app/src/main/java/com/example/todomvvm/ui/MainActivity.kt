package com.example.todomvvm.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todomvvm.R
import com.example.todomvvm.data.Todo
import com.example.todomvvm.data.TodoDatabase
import com.example.todomvvm.data.TodoRepository
import com.example.todomvvm.databinding.ActivityMainBinding
import com.example.todomvvm.ui.viewmodel.TodoViewModel
import com.example.todomvvm.ui.viewmodel.TodoViewModelFactory
import com.example.todomvvm.untils.TodoRecyclerAdapter

class MainActivity : AppCompatActivity() ,TodoRecyclerAdapter.onCheckListener{
    lateinit var binding: ActivityMainBinding
    lateinit var todoRecyclerAdapter: TodoRecyclerAdapter
    lateinit var todoViewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initUI()
    }

    private fun initUI(){
        val dao = TodoDatabase.getInstance(this).todoDao()
        val repository = TodoRepository(dao)
        val factory = TodoViewModelFactory(repository)
        todoViewModel = ViewModelProvider(this,factory).get(TodoViewModel::class.java)
        todoRecyclerAdapter = TodoRecyclerAdapter(this)
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.todoRecyclerView.setHasFixedSize(true)
        binding.todoRecyclerView.adapter = todoRecyclerAdapter
        observeData()
        swipeHeler()
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this,AddTodoActivity::class.java)
            startActivityForResult(intent,1)
        }
    }

    private fun observeData(){
        todoViewModel.todoList.observe(this, Observer {
            todoRecyclerAdapter.submitList(it)
            binding.todoRecyclerView.adapter!!.notifyDataSetChanged()
        })
    }

    private fun swipeHeler(){
         val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
             override fun onMove(
                 recyclerView: RecyclerView,
                 viewHolder: RecyclerView.ViewHolder,
                 target: RecyclerView.ViewHolder
             ): Boolean {
                 TODO("Not yet implemented")
             }

             override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                 todoViewModel.delete(todoViewModel.todoList.value!![viewHolder.adapterPosition])

             }

         }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.todoRecyclerView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == 1){
            val bundle = data!!.getBundleExtra("key")
            val todo = bundle.getParcelable<Todo>("bundle")
            todoViewModel.insert(todo!!)
        }
    }

    override fun onCheckBox(position: Int, state: Boolean) {
        val item = todoViewModel.todoList.value!![position]
        todoViewModel.update(Todo(item.id,item.title,item.time,state))
    }
}