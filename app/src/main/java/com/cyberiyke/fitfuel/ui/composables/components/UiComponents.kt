package com.cyberiyke.fitfuel.ui.composables.components

import android.net.Uri
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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