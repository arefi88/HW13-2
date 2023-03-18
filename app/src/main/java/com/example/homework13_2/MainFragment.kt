package com.example.homework13_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.homework13_2.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var listTask:MutableList<Task>
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val mainViewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return binding.root
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner=viewLifecycleOwner
        listTask= mutableListOf()

        val listState = State.values()
        val rand= Random()
        var n: Int


        viewPagerAdapter= ViewPagerAdapter(this)
        val tabLayout=view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager=view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter=viewPagerAdapter
        TabLayoutMediator(tabLayout,viewPager){tab,position->
            tab.text=listState[position].toString()
            binding.fabTask.setOnClickListener {
                when(mainViewModel.randomState()){
                    "Todo"->{
                        tabLayout.getTabAt(0)?.select()
                    }
                    "Doing"->{
                        tabLayout.getTabAt(1)?.select()
                    }
                    "Done"->{
                        tabLayout.getTabAt(2)?.select()
                    }

                }
                mainViewModel.updateListTask(mainViewModel.listTask)
            }

        }.attach()




    }
}