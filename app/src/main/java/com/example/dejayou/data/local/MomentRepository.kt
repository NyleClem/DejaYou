package com.example.dejayou.data.local

import kotlinx.coroutines.flow.Flow

class MomentRepository(private val momentDao: MomentDAO) {
    suspend fun insertMoment(moment: Moment){
        momentDao.insertMoment(moment)
    }

    fun getAllMoments() : Flow<List<Moment>> {
       return momentDao.getAllMoments()
    }

    suspend fun updateMoment(moment: Moment){
        momentDao.updateMoment(moment)
    }

    suspend fun deleteMoment(moment: Moment){
        momentDao.deleteMoment(moment)
    }
}