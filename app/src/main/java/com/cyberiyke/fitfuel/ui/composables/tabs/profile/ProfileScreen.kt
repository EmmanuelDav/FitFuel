package com.cyberiyke.fitfuel.ui.composables.tabs.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyberiyke.fitfuel.R
import com.cyberiyke.fitfuel.data.model.User
import com.cyberiyke.fitfuel.ui.composables.components.RunningStats

/**
 * Composable function that represents the home screen of the application.
 */

@Preview(showBackground = true)
@Composable
 fun ProfileScreen() {
    Column(
        modifier = Modifier.padding(bottom =  8.dp)
    ) {
        TopBar()
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp)
        ) {
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(horizontal = 24.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    SettingsItem(
                        img = painterResource(id = R.drawable.running_man),
                        title = "Personal Parameter"
                    )
                    SettingsItem(
                        img = painterResource(id = R.drawable.stopwatch), title = "Achievements"
                    )
                    SettingsItem(
                        img = painterResource(id = R.drawable.fire), title = "Settings"
                    )
                    SettingsItem(
                        img = painterResource(id = R.drawable. baseline_male_24),
                        title = "Our Contact",
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .offset(y = (-24).dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                )
        )
        Column(modifier = modifier.padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.size(24.dp))
            TopBarProfile(
                modifier = Modifier.background(color = Color.Transparent),
                user = User(),
                isEditMode = true,
                profileEditActions = null
            )
            Spacer(modifier = Modifier.size(32.dp))
            TotalProgressCard()
        }
    }
}

@Composable
private fun SettingsItem(
    modifier: Modifier = Modifier, img: Painter, title: String
) {

    ElevatedCard(shape = RoundedCornerShape(5.dp), modifier = Modifier.padding(10.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = img,
                contentDescription = title,
                modifier = Modifier.size(32.dp),
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = title,
                style = typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.weight(1f),
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
            )
        }
    }

}


@Composable
private fun TotalProgressCard(modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = Modifier, elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Total Progress", style = typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold
                ), modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "More Info",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterVertically),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = MaterialTheme.shapes.small
                )
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RunningStats(
                painter = painterResource(id = R.drawable.running_man),
                unit = "10",
                value = "km",
                modifier = Modifier
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterVertically)
                    .background(
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                    )
            )
            RunningStats(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.stopwatch),
                unit = "hr",
                value = "20Km"
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterVertically)
                    .background(
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                    )
            )
            RunningStats(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.fire),
                unit = "kcal",
                value = "10"
            )

        }
    }


}