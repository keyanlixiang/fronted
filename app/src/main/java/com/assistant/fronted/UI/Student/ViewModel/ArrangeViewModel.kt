package com.assistant.fronted.UI.Student.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArrangeViewModel:ViewModel() {
    val refreshSignal = MutableLiveData<Int>()
    init {
        refreshSignal.value = -1
    }
    fun refreshFragment(){
        if (refreshSignal.value == -1){
            refreshSignal.value = 0
        }else if (refreshSignal.value == 0){
            refreshSignal.value = -1
        }
    }
}