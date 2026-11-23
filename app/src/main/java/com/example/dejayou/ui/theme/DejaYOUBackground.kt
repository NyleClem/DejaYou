package com.example.dejayou.ui.theme

import android.view.Display
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme

val ChampagnePeachGradient = Brush.linearGradient(listOf(
    PeachBase,
    ChampagnePeach,
    PeachHighlight,
    RoseCopper
))

val MidnightReverieGradient = Brush.linearGradient(
    listOf(
        MidnightViolet,
        MidnightIndigo,
        MidnightNavy
    )
)//TF

@Composable
fun DejaYouBackground(
    content: @Composable () -> Unit
){
    val backgroundBrush = if (
        MaterialTheme.colorScheme.background == MidnightNavy
    ){
        MidnightReverieGradient
    }else {
        ChampagnePeachGradient
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ){
        content()
    }
}