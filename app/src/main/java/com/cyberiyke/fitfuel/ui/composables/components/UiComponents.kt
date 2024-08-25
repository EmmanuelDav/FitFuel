package com.cyberiyke.fitfuel.ui.composables.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cyberiyke.fitfuel.R

import com.cyberiyke.fitfuel.data.model.Gender


@Composable
fun UserProfilePix(
    modifier: Modifier = Modifier,
    imgUri: Uri?,
    gender: Gender,
    tint: Color = MaterialTheme.colorScheme.onPrimary
) {
    AsyncImage(
        model = imgUri,
        contentDescription = null,
        modifier = Modifier,
        fallback = painterResource(
            id = if (gender == Gender.MALE) R.drawable.baseline_male_24 else R.drawable.baseline_female_24

        ),
        contentScale = ContentScale.Crop,
        colorFilter = if (imgUri == null) ColorFilter.tint(color = tint) else null
    )
}


@Composable
fun RunningStats(
    modifier: Modifier = Modifier, painter: Painter, unit: String, value: String
) {


    Row(modifier = Modifier.padding(4.dp)) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 5.dp)
                .size(20.dp)
        )

        Spacer(modifier = Modifier.size(12.dp))

        Column(modifier = Modifier.padding(top = 8.dp), horizontalAlignment = Alignment.End) {
            androidx.compose.material3.Text(
                text = value,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            )
            androidx.compose.material3.Text(
                text = unit,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall
            )

        }

    }
}