
package com.example.dejayou.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dejayou.data.local.Moment
import com.example.dejayou.ui.viewmodel.MomentViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Displays the Home screen with recent Moments, a reflection prompt,
 * and actions for accessing Settings and creating Moments.NC
 */
@Composable
fun HomeScreen(
    onSettingsClick: () -> Unit,
    momentViewModel: MomentViewModel
    ) {
    //Controls whether the Create Moment bottom sheet is displayed.NC
    var showCreateMomentSheet by remember {
        mutableStateOf(false)
    }
    // Observe saved Moments so the Home screen updates automatically. NC
    val moments by momentViewModel.allMoments.collectAsState(
        initial = emptyList()
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HomeHeader(onSettingsClick)
        RecentMoments(moments = moments.take(4))
        DailyReflectionCard()
        CreateMomentButton(
            onCreateClick = {
                showCreateMomentSheet = true
            }
        )
    }
    if (showCreateMomentSheet) {
        CreateMomentSheet(
            onDismiss = {
                showCreateMomentSheet = false },
            momentViewModel = momentViewModel

        )
    }
}

/**
 * Displays the DejaYou branding and Settings button.
 */
@Composable
fun HomeHeader(onSettingsClick: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "YOUR PERSONAL TIME CAPSULE",
                fontSize = 12.sp
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append("deja")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("YOU")
                    }
                },
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        IconButton(onClick = onSettingsClick) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings"
            )
        }
    }
}

/**
 * Displays a Moment preview with its emotion,
 * description,title, and date.
 */
@Composable
fun MomentCard(
    title: String,
    description: String,
    date: String,
    emotion: String,
    icon: ImageVector,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: (()-> Unit)? = null,
    momentImage: coil3.Bitmap? = null,
){
    // Controls if the attached picture is displayed. NC
    var showImageDialog by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .height(200.dp)
            /**add another modifier to make the card
             * clickable only when an onClick callback is provided
             * NC
            */
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Display the emotion icon and overflow menu icon.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = emotion
                )

                Icon(
                    imageVector = Icons.Filled.MoreHoriz,
                    contentDescription = "More options"
                )
            }

            Text(
                text = description,
                fontSize = 13.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$date · $emotion",
                        fontSize = 11.sp
                    )

                    // Show the picture icon only when an image is attached. NC
                    if (momentImage != null) {
                        IconButton(
                            onClick = {
                                showImageDialog = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Image,
                                contentDescription = "View attached picture"
                            )
                        }
                    }
                }
            }
        }
    }
    // Display the attached picture when the image icon is clicked. NC
    if (showImageDialog && momentImage != null) {
        AlertDialog(
            onDismissRequest = {
                showImageDialog = false
            },
            title = {
                Text("Attached Picture")
            },
            text = {
                Image(
                    bitmap = momentImage.asImageBitmap(),
                    contentDescription = "Picture attached to $title",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showImageDialog = false
                    }
                ) {
                    Text("Close")
                }
            }
        )
    }


}

/**
 * Display a two-column preview of recent Moments.
 */
@Composable
fun RecentMoments(moments: List<Moment>) {

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Your Moments",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Every feeling has a story.",
            fontSize = 14.sp
        )


        // Display saved Moments in rows of two. NC
        moments.chunked(2).forEach { rowMoments ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowMoments.forEach { moment ->

                    val (emotionIcon, cardColor) = when (moment.moodColor) {
                        "Happy" -> Icons.Filled.WbSunny to Color(0xFFFFF0CA)
                        "Nostalgic" -> Icons.Filled.Favorite to Color(0xFFE9DDFB)
                        "Hopeful" -> Icons.Filled.Star to Color(0xFFDDF2E9)
                        "Sad" -> Icons.Filled.Cloud to Color(0xFFDDEAFB)
                        else -> Icons.Filled.Star to Color(0xFFFFF0CA)
                    }

                    val formattedDate = SimpleDateFormat(
                        "MMM d, yyyy",
                        Locale.getDefault()
                    ).format(Date(moment.createdAt))

                    MomentCard(
                        title = moment.title,
                        description = moment.content,
                        date = formattedDate,
                        emotion = moment.moodColor,
                        icon = emotionIcon,
                        backgroundColor = cardColor,
                        modifier = Modifier.weight(1f),
                        momentImage = moment.momentImage
                    )
                }

                // Keep a single card at half the row width. NC
                if (rowMoments.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

/**
 * Displays a reflection prompt that encourages users
 * to capture their thoughts and emotions.
 */
@Composable
fun DailyReflectionCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2E4F8)
        )
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AutoAwesome,
                    contentDescription = null,
                    tint = Color(0xFF7442A5)
                )

                Text(
                    text = "A Moment to Reflect",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF7442A5)
                )
            }

            Text(
                text = "What's been on your mind lately?",
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF39234D)
            )
        }
    }
}

/**
 * Displays the primary action for creating a new Moment.
 */
@Composable
fun CreateMomentButton(onCreateClick: () -> Unit) {
    Button(
        onClick = onCreateClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF7043B8)
        )
    ) {
        Icon(
            imageVector = Icons.Filled.AddCircle,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Create a Moment",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}