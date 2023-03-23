package com.example.homework13_2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homework13_2.databinding.ItemTaskBinding
import kotlin.properties.Delegates


class TaskAdapterList :RecyclerView.Adapter<TaskAdapterList.ViewHolder>() {

    private lateinit var binding: ItemTaskBinding
    var onItemClick: ((View,Int) -> Unit)? = null
    private var boolean=false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding=ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position],position)
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item:Task,position: Int){
            binding.apply {
                txtTaskName.text=item.name
                txtTaskState.text=item.state.name
                if (position % 2==0){
                    card.setCardBackgroundColor(Color.BLUE)
                }else{
                    card.setCardBackgroundColor(Color.RED)

                }

                card.setOnClickListener {view->
                    if (!boolean){
                        view.setBackgroundColor(Color.GREEN)
                        boolean=true

                    }else{
                        if (position % 2==0){
                            view.setBackgroundColor(Color.BLUE)
                            boolean=false

                        }else{
                            view.setBackgroundColor(Color.RED)
                            boolean=false

                        }

                    }
                }
            }

        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,differCallback)

}