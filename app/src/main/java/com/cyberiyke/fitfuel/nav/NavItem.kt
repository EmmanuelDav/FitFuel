package com.cyberiyke.fitfuel.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

sealed class NavItem {
    object Home:
            Item(path = NavPath.HOME.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)

    object Events:
            Item(path = NavPath.EVENTS.toString(), title = NavTitle.EVENTS, icon = Icons.Default.Search)

    object News:
            Item(path = NavPath.NEWS.toString(), title = NavTitle.NEWS, icon = Icons.AutoMirrored.Default.List)

    object Settings :
            Item(path = NavPath.SETTINGS.toString(), title = NavTitle.SETTINGS, icon = Icons.Default.Person)

}