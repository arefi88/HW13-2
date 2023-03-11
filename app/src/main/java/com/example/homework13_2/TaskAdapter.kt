package com.example.homework13_2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework13_2.databinding.ItemTaskBinding

class TaskAdapter(private val list: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val itemBinding:ItemTaskBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bindItem(task: Task,position: Int){
            itemBinding.txtTaskName.text=task.name
            itemBinding.txtTaskState.text=task.state.name
            if (position % 2==0){
                itemBinding.card.setCardBackgroundColor(Color.BLUE)

            }else{
                itemBinding.card.setCardBackgroundColor(Color.RED)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
       val binding=ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount()=list.size



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task=list[position]
        holder.bindItem(task,position)
    }
    fun addItem(task: Task){
        list.add(task)
        notifyDataSetChanged()
    }
}