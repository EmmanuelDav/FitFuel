package com.cyberiyke.fitfuel.ui.composables.tabs.profile

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyberiyke.fitfuel.R
import com.cyberiyke.fitfuel.data.model.User
import com.cyberiyke.fitfuel.ui.composables.components.RunningStats

/**
 * Composable function that represents the home screen of the application.
 */

@Preview(showBackground = true)
@Composable
fun ProfileScreen() {

    val context = LocalContext.current
    val viewModel: ProfileViewModel = hiltViewModel()
    val state by viewModel.profileScreenState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.errorMsg) {
        if (state.errorMsg.isNullOrBlank().not())
            Toast.makeText(context, state.errorMsg.toString(), Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        TopBar(state = state, profileEditAction = viewModel,)
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                Text(
                    text = "General".toUpperCase(), style = typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold
                    ), modifier = Modifier.padding(6.dp)
                )

                SettingsItem(
                    img = painterResource(id = R.drawable.analytics),
                    title = "Statistics"
                )
                SettingsItem(
                    img = painterResource(id = R.drawable.success), title = "Achievements"
                )

                Text(
                    text = "Nutritional Info".toUpperCase(), style = typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold
                    ), modifier = Modifier.padding(6.dp)
                )

                SettingsItem(
                    img = painterResource(id = R.drawable.meal),
                    title = "Meal Tracker",
                )
                SettingsItem(
                    img = painterResource(id = R.drawable.calories),
                    title = "Daily Caloric Intake",
                )

                Text(
                    text = "Settings and Privacy".toUpperCase(), style = typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold
                    ), modifier = Modifier.padding(6.dp)
                )

                SettingsItem(
                    img = painterResource(id = R.drawable.setting),
                    title = "App Settings",
                )

                SettingsItem(
                    img = painterResource(id = R.drawable.policy),
                    title = "Private Policy",
                )

                SettingsItem(
                    img = painterResource(id = R.drawable.contact),
                    title = "Contact Us",
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    state: ProfileScreenState,
    profileEditAction: ProfileEditAction,
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
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                )
        )
        Column(modifier = modifier.padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.size(24.dp))
            TopBarProfile(
                modifier = Modifier.background(color = Color.Transparent),
                user = state.user,
                isEditMode = state.isEditMode,
                profileEditActions = profileEditAction
            )
            Spacer(modifier = Modifier.size(32.dp))
            TotalProgressCard(state = state)
        }
    }
}

@Composable
private fun SettingsItem(
    modifier: Modifier = Modifier, img: Painter, title: String
) {

    ElevatedCard(
        shape = RoundedCornerShape(5.dp), modifier = modifier
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 5.dp),
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
private fun TotalProgressCard(modifier: Modifier = Modifier, state: ProfileScreenState) {
    ElevatedCard(
        modifier = modifier, elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = modifier
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
                unit = "km",
                value = state.totalDistanceInKm.toString(),
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
                value = state.totalDurationInHr.toString()
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
                value = state.totalCaloriesBurnt.toString()
            )

        }
    }


}