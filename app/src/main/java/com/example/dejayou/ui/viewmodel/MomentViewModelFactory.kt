package com.example.dejayou.ui.viewmodel

import androidx.lifecycle.*
import com.example.dejayou.data.local.MomentRepository

class MomentViewModelFactory(private val repository: MomentRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MomentViewModel::class.java)){
            @Suppress("UNCHECKED_CAST") //TF
            return MomentViewModel(repository) as T //TF
        }
        throw IllegalArgumentException("Unknown ViewModel class") //TF
    }
}