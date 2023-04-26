package com.example.phonetextfield

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneTextFieldViewModel @Inject constructor() : ViewModel(){

    var areaCodeList = mutableStateOf(listOf("a","b","c"))
        private set

}