package com.example.dejayou.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "moments") //TF
data class Moment( //TF
    @PrimaryKey(autoGenerate = true) //TF
    val id: Int = 0, //TF
    val title: String, //TF
    val content: String, //TF
    val moodColor: String, //TF
    val createdAt: Long = System.currentTimeMillis() //TF
)
