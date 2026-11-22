package com.example.dejayou.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dejayou.data.local.MomentRepository
import androidx.lifecycle.viewModelScope
import com.example.dejayou.data.local.Moment
import kotlinx.coroutines.launch


class MomentViewModel(private val repository: MomentRepository) : ViewModel() {//TF
    val allMoments = repository.getAllMoments()//TF

    fun insertMoment(moment: Moment, onSuccess: () -> Unit) { //TF
        viewModelScope.launch {
            repository.insertMoment(moment)
            onSuccess() // NC: Notify the UI after the Moment is saved.
        }
    }
    fun updateMoment(moment: Moment){
        viewModelScope.launch { repository.updateMoment(moment) } //TF
    }
    fun deleteMoment(moment: Moment){
        viewModelScope.launch { repository.deleteMoment(moment) } //TF
    }
}