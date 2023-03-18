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
import com.example.homework13_2.databinding.FragmentDoingBinding

class DoingFragment : Fragment(R.layout.fragment_doing) {
    lateinit var binding:FragmentDoingBinding
    private val taskListDoing= mutableSetOf<Task>()
    private val mainViewModel:MainViewModel by activityViewModels()
    private val taskAdapterList by lazy { TaskAdapterList() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_doing,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner=viewLifecycleOwner
        var index=-1
        mainViewModel.listTaskLiveData.observe(viewLifecycleOwner){tasks->

            for (i in tasks.indices){
                if (tasks[i].state.name=="Doing"){
                    taskListDoing.add(tasks[i])
                }
            }
            taskAdapterList.differ.submitList(taskListDoing.toList())

        }

        binding.rvTaskDoing.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter=taskAdapterList
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation==2){
            binding.rvTaskDoing.layoutManager=GridLayoutManager(requireContext(),2)
        }else{
            binding.rvTaskDoing.layoutManager=LinearLayoutManager(activity)
        }
    }
}