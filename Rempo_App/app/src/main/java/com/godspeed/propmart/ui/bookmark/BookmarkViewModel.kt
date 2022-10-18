package com.godspeed.propmart.ui.Bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookmarksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Bookmarks Fragment"
    }
    val text: LiveData<String> = _text
}