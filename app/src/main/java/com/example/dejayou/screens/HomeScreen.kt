
package com.example.dejayou.screens

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
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * Displays the Home screen with recent Moments, a reflection prompt,
 * and actions for accessing Settings and creating Moments.
 */
@Composable
fun HomeScreen(onSettingsClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HomeHeader(onSettingsClick)
        RecentMoments()
        DailyReflectionCard()
        CreateMomentButton(
            onCreateClick = {
                //TODO: Open the Create Moment bottom sheet

            }
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
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(20.dp)
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

                Text(
                    text = "$date · $emotion",
                    fontSize = 11.sp
                )
            }
        }
    }
}

/**
 * Displays a two-column preview of recent Moments.
 * Uses sample data until Moment database is implemented.
 */
@Composable
fun RecentMoments() {
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

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MomentCard(
                title = "A Beautiful Day",
                description = "Everything seemed to fall into place today...",
                date = "Oct 30, 2026",
                emotion = "Happy",
                icon = Icons.Filled.WbSunny,
                backgroundColor = Color(0xFFFFF0CA),
                modifier = Modifier.weight(1f)
            )

            MomentCard(
                title = "Missing the Old Days",
                description = "Sometimes I wish I could go back and experience those moments again.",
                date = "Oct 27, 2026",
                emotion = "Nostalgic",
                icon = Icons.Filled.Favorite,
                backgroundColor = Color(0xFFE9DDFB),
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MomentCard(
                title = "A Fresh Start",
                description = "I'm finally starting to see the progress I've been working toward.",
                date = "Oct 24, 2026",
                emotion = "Hopeful",
                icon = Icons.Filled.Star,
                backgroundColor = Color(0xFFDDF2E9),
                modifier = Modifier.weight(1f)
            )

            MomentCard(
                title = "One of Those Days",
                description = "Nothing really went the way I wanted, but tomorrow is another chance.",
                date = "Oct 20, 2026",
                emotion = "Sad",
                icon = Icons.Filled.Cloud,
                backgroundColor = Color(0xFFDDEAFB),
                modifier = Modifier.weight(1f)
            )
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

