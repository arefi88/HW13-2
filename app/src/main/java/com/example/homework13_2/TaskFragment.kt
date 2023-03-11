package com.example.homework13_2

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework13_2.databinding.FragmentTaskBinding
import java.util.Random

class TaskFragment : Fragment(R.layout.fragment_task) {
    private lateinit var binding:FragmentTaskBinding
    private  var listTask= mutableListOf<Task>()
    private val taskAdapter = TaskAdapter(listTask)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_task,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner=viewLifecycleOwner

        val number= activity?.intent?.getStringExtra("number")?.toInt()
        val listState = State.values()
        val rand=Random()
        var n: Int
        for (i in 0 until number!!){
            n=rand.nextInt(3)
            listTask.add(i, Task(activity?.intent?.getStringExtra("username").toString(),listState[n]))
        }

        binding.rvTask.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=taskAdapter
        }
        binding.fabTask.setOnClickListener {
            n=rand.nextInt(3)
            taskAdapter.addItem(Task(activity?.intent?.getStringExtra("username").toString(),listState[n]))
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation==2){
            binding.rvTask.layoutManager=(GridLayoutManager(requireContext(),2))
        }else{
           binding.rvTask.layoutManager=LinearLayoutManager(activity)
        }
    }

}