package com.example.dejayou.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Moment::class], version = 1) //TF
abstract class MomentDatabase() : RoomDatabase() { //TF
    abstract fun momentDao(): MomentDAO //TF
}