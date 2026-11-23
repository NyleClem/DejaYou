package com.example.dejayou.screens
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
/**
 * Reusable Photo Picker for attaching images to Moments. NC
 */
@Composable
fun MomentImagePicker(
    onImageSelected: (Uri) -> Unit,
    // for another composable to provide its own button. NC
    content:
    @Composable (onPickImage: () -> Unit) -> Unit
) {
// Launch the system Photo Picker and return the selected image URI. NC
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            onImageSelected(uri)
        }
    }
    content {
        photoPickerLauncher.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }
}
