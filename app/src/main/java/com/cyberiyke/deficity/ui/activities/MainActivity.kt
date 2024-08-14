package com.cyberiyke.deficity.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cyberiyke.deficity.R
import com.cyberiyke.deficity.nav.NavItem
import com.cyberiyke.deficity.ui.composables.tabs.EventScreen
import com.cyberiyke.deficity.ui.composables.tabs.HomeScreen
import com.cyberiyke.deficity.ui.composables.tabs.NewsScreen
import com.cyberiyke.deficity.ui.composables.tabs.SettingScreen
import com.cyberiyke.deficity.ui.theme.DefiCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DefiCityTheme {
                var navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(bottomBar = {
        BottomAppBar {
            BottomBar(navController = navController)
        }
    }, floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        NavItem.Home.path
                    )
                },
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.absoluteOffset(y = 37.dp)

            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_directions_run_24),
                    contentDescription = "",
                )

            }
    },
        floatingActionButtonPosition = FabPosition.Center,
        content = { paddingValues ->
        NavigationScreens(navController = navController, modifier = Modifier.padding(paddingValues))

    })
}

@Composable
private fun BottomBar(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary,
                clip = true
            )
    ) {
        BottomNavigationBar(navController = navController)
    }
}

@Composable
fun NavigationScreens(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = NavItem.Home.path) {
        composable(NavItem.Home.path) { HomeScreen() }
        composable(NavItem.Events.path) { EventScreen() }
        composable(NavItem.News.path) { NewsScreen() }
        composable(NavItem.Settings.path) { SettingScreen() }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navItems = listOf(NavItem.Home, NavItem.Events, NavItem.News, NavItem.Settings)
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(alwaysShowLabel = true,
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.path) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.title) },
            )
        }
    }

}
