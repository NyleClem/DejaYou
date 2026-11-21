package com.example.dejayou.data.local

import android.graphics.*
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.ByteArrayOutputStream
import java.io.OutputStream

class ImageConverter {
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray? {
        if (bitmap == null) return null
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap?{
        return byteArray?.let{
            BitmapFactory.decodeByteArray(it, 0, it.size)
        }
    }
}

@Entity(tableName = "moments") //TF
@TypeConverters(ImageConverter::class)
data class Moment( //TF
    @PrimaryKey(autoGenerate = true) //TF
    val id: Int = 0, //TF
    val title: String, //TF
    val content: String, //TF
    val moodColor: String, //TF
    val momentImage: Bitmap? = null, //TF
    val createdAt: Long = System.currentTimeMillis() //TF
)
