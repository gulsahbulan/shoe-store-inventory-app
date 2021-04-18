package com.gbulan.shoestore.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gbulan.shoestore.model.Shoe


class SharedViewModel: ViewModel()  {

    private val shoeList = ArrayList<Shoe>()

    private val _shoeData = MutableLiveData<ArrayList<Shoe>>()
    val shoeData: LiveData<ArrayList<Shoe>>
        get() = _shoeData

    private val _eventClick = MutableLiveData<Boolean>()
    val eventClick: LiveData<Boolean>
        get() = _eventClick

    fun save(shoe: Shoe) {
        shoeList.add(shoe)
        _shoeData.value = shoeList
        _eventClick.value =  true
    }

    fun navigate() {
        _eventClick.value =  true
    }

    fun navigationCompleted() {
        _eventClick.value =  false
    }
}