package com.toreva.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toreva.mobile.ui.activity.ActivityScreen
import com.toreva.mobile.ui.onboarding.ConnectWalletScreen
import com.toreva.mobile.ui.onboarding.CreateVaultScreen
import com.toreva.mobile.ui.strategies.ConfirmIntentScreen
import com.toreva.mobile.ui.strategies.StrategyListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "connect") {
        composable("connect") { ConnectWalletScreen { navController.navigate("vault") } }
        composable("vault") { CreateVaultScreen { navController.navigate("strategies") } }
        composable("strategies") { StrategyListScreen(onActivate = { navController.navigate("confirm") }, onActivity = { navController.navigate("activity") }) }
        composable("confirm") { ConfirmIntentScreen { navController.navigate("activity") } }
        composable("activity") { ActivityScreen() }
    }
}
