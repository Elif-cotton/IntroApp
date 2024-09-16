package com.example.introjetpack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.introjetpack.dataStore.StoreBoarding
import com.example.introjetpack.onBoardingViews.MainOnBoarding
import com.example.introjetpack.views.HomeView
import com.example.introjetpack.views.SplashScreen

@Composable
fun NavManager(modifier: Modifier) {
    val context = LocalContext.current
    val dataStore = StoreBoarding(context)
    val store = dataStore.getBoarding.collectAsState(initial = false)

    val navController = rememberNavController()
    NavHost(navController, startDestination = if (store.value) "Home" else "Splash") {
        composable("OnBoarding") {
            MainOnBoarding(modifier = Modifier, navController, dataStore)
        }
        composable("Home") {
            HomeView()
        }
        composable("Splash") {
            SplashScreen(navController, store.value)
        }
    }
}