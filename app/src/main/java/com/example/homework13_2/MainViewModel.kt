package com.example.homework13_2

import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    val indexLiveData=MutableLiveData<Int>()
    val isSelect=MutableLiveData<Boolean>()
    val backgroundView=MutableLiveData<Int>()
    var listTask:MutableList<Task> = mutableListOf()
    val counter=MutableLiveData(0)
    val listTaskLiveData=MutableLiveData<MutableList<Task>>()
    val task=MutableLiveData<Task>()
    private val listState = State.values()
    val rand= Random()
    var count=0
    private var n=-1


    fun setBackground(color:Int){
       backgroundView.value=color
    }

    fun updateTask(taskName: Task){
        task.value=taskName
    }

    fun updateListTask(list: MutableList<Task>){
        listTaskLiveData.value=list
    }
    fun updateCounter(count: Int){
        counter.value=count
    }

    fun randomState():String{
        count++
        n = rand.nextInt(3)
        listTask.add(Task("work $count",listState[n]))
        return listState[n].name
    }
}