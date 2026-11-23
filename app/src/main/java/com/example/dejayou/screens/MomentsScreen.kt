package com.example.dejayou.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dejayou.data.local.Moment
import com.example.dejayou.ui.viewmodel.MomentViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Displays all saved Moments from the database. NC
 */
@Composable
fun MomentsScreen(momentViewModel: MomentViewModel) {

    // Observe saved Moments so the screen updates automatically. NC
    val moments by momentViewModel.allMoments.collectAsState(
        initial = emptyList()
    )
    var selectedMoment by remember{
        mutableStateOf<Moment?>(null)
    }

    // Display all saved Moments in a scrollable list. NC
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = moments.chunked(2),
            key = { row -> row.first().id }
        ) { rowMoments ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
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
                        momentImage = moment.momentImage,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            selectedMoment = moment
                        }
                    )
                }
                // Preserve card width when a row has only one Moment. NC
                if (rowMoments.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
            }

    val moment = selectedMoment
    /** Display the selected Moment's full details
     * in a scrollable dialog.NC
     */
    if (moment != null) {
        AlertDialog(
            onDismissRequest = {
                selectedMoment = null
            },
            title = {
                Text(moment.title)
            },
            text = {
                Column(
                    modifier = Modifier
                        .heightIn(max = 400.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "${
                            SimpleDateFormat(
                                "MMM d, yyyy",
                                Locale.getDefault()
                            ).format(Date(moment.createdAt))
                        } · ${moment.moodColor}"
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(moment.content)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        selectedMoment = null
                    }
                ) {
                    Text("Close")
                }
            }
        )
    }
        }
