package com.example.dejayou.data.local

import android.content.Context
import androidx.room.*

//WILL PROVIDE SHARED DATABASE INSTANCE

object DatabaseProvider{
    @Volatile //updates will be visible across all threads
    private var INSTANCE: MomentDatabase? = null //TF

    fun getDatabase(context: Context) : MomentDatabase {
       if( INSTANCE != null ){ //TF
           return INSTANCE!! //TF
       } else {
           synchronized(this){ //TF
               if(INSTANCE == null){ //TF
                   INSTANCE = Room.databaseBuilder( //TF
                    context.applicationContext, //TF
                    MomentDatabase::class.java, //TF
                    "dejayou_database" //TF
                ).build() //TF
               }
           }
            return INSTANCE!! //TF
       }
    }
}

