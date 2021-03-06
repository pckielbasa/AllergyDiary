package com.example.praca_inz.ui.food

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.praca_inz.network.FoodApiFilter
import com.example.praca_inz.network.UserApi
import com.example.praca_inz.property.MyFoodProperty
import com.example.praca_inz.ui.food.FoodGridAdapter.FoodGridStatus
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel(){
    private val _goToAddFood = MutableLiveData<Boolean>()
    val goToAddFood : LiveData<Boolean>
        get() = _goToAddFood

    fun addFoodStart(){
        _goToAddFood.value = true
    }
    fun addFoodFinish(){
        _goToAddFood.value = false
    }

    private val _status = MutableLiveData<FoodGridStatus>()

    val status: LiveData<FoodGridStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MyFoodProperty>>()

    val properties: LiveData<List<MyFoodProperty>>
        get() = _properties



    private val _navigateToSelectedProperty = MutableLiveData<MyFoodProperty>()

    val navigateToSelectedProperty: LiveData<MyFoodProperty>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getFoodProperties(FoodApiFilter.SHOW_MEAL)
    }

    private fun getFoodProperties(filter: FoodApiFilter){
        coroutineScope.launch {
            val username = FirebaseAuth.getInstance().currentUser!!.uid
            Log.i("retrofit", username)
            val getPropertiesDeferred = UserApi.retrofitService.getMyFoodsAsync(filter.type, username)
            try {
                _status.value = FoodGridStatus.LOADING
                filter.type
                val listResult =  getPropertiesDeferred.await()
                _status.value = FoodGridStatus.DONE
                _properties.value = listResult
                if (listResult.isEmpty()){
                    _status.value = FoodGridStatus.EMPTY
                }
            } catch (e: Exception) {
                _status.value = FoodGridStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun displayPropertyDetails(myFoodProperty: MyFoodProperty) {
        _navigateToSelectedProperty.value = myFoodProperty
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
    fun updateFilter(filter: FoodApiFilter) {
        getFoodProperties(filter)
    }

}