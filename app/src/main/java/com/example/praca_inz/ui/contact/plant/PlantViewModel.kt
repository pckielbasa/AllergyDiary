package com.example.praca_inz.ui.contact.plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.praca_inz.network.ContactApi
import com.example.praca_inz.network.FoodApi
import com.example.praca_inz.property.ContactProperty
import com.example.praca_inz.property.FoodProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getPlantRealEstateProperties()
    }

    private fun getPlantRealEstateProperties(){
        coroutineScope.launch {
            val getPropertiesDeferred = ContactApi.retrofitService.getContacts()
            try {
                val listResult = getPropertiesDeferred.await()
                _response.value = "Success: ${listResult.size} Meals properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}