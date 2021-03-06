package com.example.praca_inz.ui.calendar.selectFood.addFoodToDay

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.praca_inz.property.MyFoodProperty

class AddFoodToDayViewModel (myFoodProperty: MyFoodProperty,
                             app: Application): AndroidViewModel(app) {
    private val _goToSelectFood = MutableLiveData<Boolean>()
    val goToSelectFood : LiveData<Boolean>
        get() = _goToSelectFood

    fun selectFoodStart(){
        _goToSelectFood.value = true
    }
    fun selectFoodFinish(){
        _goToSelectFood.value = false
    }

    private val _openNavCalendar = MutableLiveData<Boolean>()
    val openNavCalendar : LiveData<Boolean>
        get() = _openNavCalendar

    fun changeDate(){
        _openNavCalendar.value = true
    }

    fun changeDateFinish(){
        _openNavCalendar.value = false
    }

    private val _goToChangeTime = MutableLiveData<Boolean>()
    val goToChangeTime : LiveData<Boolean>
        get() = _goToChangeTime

    fun changeTime(){
        _goToChangeTime.value = true
    }
    fun changeTimeFinish(){
        _goToChangeTime.value = false
    }

    private val _selectedProperty = MutableLiveData<MyFoodProperty>()
    val selectedProperty: LiveData<MyFoodProperty>
        get() = _selectedProperty


    init {
        _selectedProperty.value = myFoodProperty
    }

}