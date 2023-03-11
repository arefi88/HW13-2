package com.example.homework13_2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val numberTask=MutableLiveData<Int>()
    val listTask=MutableLiveData<List<Task>>()
    fun updateNumberTask(number:Int){
        numberTask.value=number
    }
    fun updateListTask(list: List<Task>){
        listTask.value=list
    }
}