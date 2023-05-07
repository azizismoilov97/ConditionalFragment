package com.example.conditionalfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _authenticated = MutableLiveData<Boolean>()
    val authenticated:LiveData<Boolean> = _authenticated

    fun login(username: String, password: String) {
        _authenticated.value = username=="aziz"&&password=="ismoilov"
    }

}