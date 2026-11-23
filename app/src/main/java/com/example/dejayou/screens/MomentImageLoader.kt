
package com.example.dejayou.screens

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.bitmapConfig
import coil3.toBitmap

/**
 * Loads a selected picture using Coil for Moment storage. NC
 * Returns null if the picture cannot be loaded.
 */
suspend fun loadMomentImage(
    context: Context,
    uri: Uri
): Bitmap? {
    return try {
        val request = ImageRequest.Builder(context)
            .data(uri)
            .size(1024, 1024)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .build()

        val result = ImageLoader(context).execute(request)

        if (result is SuccessResult) {
            result.image.toBitmap()
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}
