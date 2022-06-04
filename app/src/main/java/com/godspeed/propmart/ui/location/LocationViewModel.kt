package com.godspeed.propmart.ui.Location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Location Fragment"
    }
    val text: LiveData<String> = _text
}