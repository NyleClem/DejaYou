package com.example.dejayou.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface MomentDAO {//TF

    @Insert //Create Moment
    suspend fun insertMoment(moment: Moment)//TF

    @Query("SELECT * FROM moments ORDER BY createdAt DESC")//Retrieve moments
    fun getAllMoments(): Flow<List<Moment>>//TF

    @Update//update moment
    suspend fun updateMoment(moment: Moment) //TF

    @Delete
    suspend fun deleteMoment(moment: Moment) //TF

}