package com.example.dejayou.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dejayou.ui.theme.DejaYouBackground
import com.example.dejayou.ui.theme.DejaYouTheme

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    hasCameraAccess: Boolean,
    onCameraAC: (Boolean) -> Unit,
    hasGalleryAccess: Boolean,
    onGalleryAC: (Boolean) -> Unit,
    onDeleteAllMoments: () -> Unit) {//TF
    var showDeleteDialog by remember { mutableStateOf(false) }
    Column {
        Row (modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)){
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }
            Text(text = "Settings")
        }
        Text(text = "Theme",
            fontSize = 20.sp )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
        )
        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)){
            Switch (
                checked = isDarkTheme,
                onCheckedChange = onThemeChange //TF
            )
                Text(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .65f),
                    text = if (isDarkTheme) {
                        "Dream Reverie"
                    }else{
                        "Champagne Peach"
                    }
                )
        }
        Text(text = "Camera Access",
            fontSize = 20.sp )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Switch(
                checked = hasCameraAccess,
                onCheckedChange = onCameraAC
            )
            Text (color = MaterialTheme.colorScheme.onBackground.copy(alpha = .65f),
                text = if (hasCameraAccess){
                    "Enabled"
                }else{
                    "Disabled"
                }
            )
        }
        Text(text = "Gallery Access",
            fontSize = 20.sp )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Switch(
                checked = hasGalleryAccess,
                onCheckedChange = onGalleryAC
            )
            Text (color = MaterialTheme.colorScheme.onBackground.copy(alpha = .65f),
                text = if (hasGalleryAccess){
                    "Enabled"
                }else{
                    "Disabled"
                }
            )
        }
        Text(text = "Erase Content",
            fontSize = 20.sp )
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))//TF
        ClearData("ERASE ALL CONTENT"){//TF
            showDeleteDialog = true //TF
        }
    }
    if (showDeleteDialog){
        AlertDialog(
            onDismissRequest = {showDeleteDialog = false},
            title = {Text ("Delete all Moments?")},
            text = {Text ("All saved entries will be deleted and cannot be recovered")},
            confirmButton = {
                TextButton(onClick = {
                    onDeleteAllMoments()
                    showDeleteDialog = false
                }){ Text("Delete All") }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                }){ Text("Cancel")}
            }
        )
    }
}

@Composable
fun ClearData(text: String, onDeleteClick: () -> Unit){
    Row (modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Text (
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .65f),
            text = text
        )
        IconButton(onClick = onDeleteClick) {
            Icon(
                imageVector = Icons.Default.DeleteForever,
                contentDescription = "Erase all data",
                tint = Color.Red
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview(){
    DejaYouTheme{
        DejaYouBackground {
            SettingsScreen(
                onBackClick = {},
                isDarkTheme = false,
                onThemeChange = {},
                hasCameraAccess = false,
                onCameraAC = {},
                hasGalleryAccess = false,
                onGalleryAC = {},
                onDeleteAllMoments = {}
                )
        }
    }
}
