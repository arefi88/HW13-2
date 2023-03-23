package com.example.homework13_2

import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework13_2.databinding.FragmentDoneBinding

class DoneFragment : Fragment(R.layout.fragment_done) {
    lateinit var binding:FragmentDoneBinding
    private val taskListDone= mutableSetOf<Task>()
    private val mainViewModel:MainViewModel by activityViewModels()
    private val taskAdapterList by lazy { TaskAdapterList() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_done,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner=viewLifecycleOwner
        mainViewModel.listTaskLiveData.observe(viewLifecycleOwner){ tasks ->

            for (i in tasks.indices){
                if (tasks[i].state.name=="Done"){
                    taskListDone.add(tasks[i])
                }
            }
            taskAdapterList.differ.submitList(taskListDone.toList())

        }
        binding.rvTaskDone.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter=taskAdapterList
        }

    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation==2){
            binding.rvTaskDone.layoutManager= GridLayoutManager(requireContext(),2)
        }else{
            binding.rvTaskDone.layoutManager=LinearLayoutManager(activity)
        }
    }
}