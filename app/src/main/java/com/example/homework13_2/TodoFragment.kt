package com.example.homework13_2

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework13_2.databinding.FragmentTodoBinding

class TodoFragment : Fragment(R.layout.fragment_todo) {
    private val mainViewModel:MainViewModel by activityViewModels()
    lateinit var binding: FragmentTodoBinding
    private val taskListTodo= mutableSetOf<Task>()
     private val taskAdapterList by lazy { TaskAdapterList() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_todo,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner=viewLifecycleOwner
        mainViewModel.listTaskLiveData.observe(viewLifecycleOwner){tasks->
            for (i in tasks.indices){
                if (tasks[i].state.name=="Todo"){
                    taskListTodo.add(tasks[i])
                }
            }
            taskAdapterList.differ.submitList(taskListTodo.toList())
        }


        binding.rvTaskTodo.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=taskAdapterList
        }

    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation==2){
            binding.rvTaskTodo.layoutManager= GridLayoutManager(requireContext(),2)
        }else{
            binding.rvTaskTodo.layoutManager=LinearLayoutManager(activity)
        }
    }
}