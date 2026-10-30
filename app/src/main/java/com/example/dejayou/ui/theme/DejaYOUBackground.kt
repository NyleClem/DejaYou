package com.example.dejayou.ui.theme

import android.view.Display
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

val ChampagnePeachGradient = Brush.linearGradient(listOf(
    PeachBase,
    ChampagnePeach,
    PeachHighlight,
    RoseCopper
))

@Composable
fun DejaYouBackground(
    content: @Composable () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ChampagnePeachGradient)
    ){
        content()
    }
}