package com.godspeed.propmart.ui.Hompage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HompageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Hompage Fragment"
    }
    val text: LiveData<String> = _text
}