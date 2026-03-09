package com.toreva.mobile.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.toreva.mobile.ui.activity.ActivityScreen
import com.toreva.mobile.ui.components.BottomNavDay1
import com.toreva.mobile.ui.components.NavItem
import com.toreva.mobile.ui.onboarding.ConnectWalletScreen
import com.toreva.mobile.ui.onboarding.CreateVaultScreen
import com.toreva.mobile.ui.strategies.ConfirmIntentScreen
import com.toreva.mobile.ui.strategies.StrategyListScreen
import com.toreva.mobile.ui.theme.Day1Colors

private val mainTabs = listOf(
    NavItem("strategies", Icons.Outlined.Email, "Act"),
    NavItem("activity", Icons.AutoMirrored.Outlined.List, "View"),
    NavItem("fund", Icons.Outlined.AccountBox, "Fund"),
)

private val tabRoutes = mainTabs.map { it.route }.toSet()

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBottomBar = currentRoute in tabRoutes

    Scaffold(
        containerColor = Day1Colors.Background,
        bottomBar = {
            if (showBottomBar) {
                BottomNavDay1(
                    items = mainTabs,
                    currentRoute = currentRoute ?: "",
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo("strategies") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Day1Colors.Background)
                .padding(innerPadding),
        ) {
            NavHost(navController = navController, startDestination = "connect") {
                composable("connect") {
                    ConnectWalletScreen { navController.navigate("vault") { popUpTo("connect") { inclusive = true } } }
                }
                composable("vault") {
                    CreateVaultScreen { navController.navigate("strategies") { popUpTo("vault") { inclusive = true } } }
                }
                composable("strategies") {
                    StrategyListScreen(
                        onActivate = { navController.navigate("confirm") },
                        onActivity = {
                            navController.navigate("activity") {
                                popUpTo("strategies") { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
                composable("confirm") {
                    ConfirmIntentScreen { navController.navigate("activity") { popUpTo("strategies") } }
                }
                composable("activity") { ActivityScreen() }
                composable("fund") {
                    FundPlaceholder()
                }
            }
        }
    }
}

@Composable
private fun FundPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Day1Colors.Background),
        contentAlignment = androidx.compose.ui.Alignment.Center,
    ) {
        androidx.compose.material3.Text(
            text = "Fund",
            style = com.toreva.mobile.ui.theme.Day1Type.title2,
            color = Day1Colors.TextPrimary,
        )
    }
}
