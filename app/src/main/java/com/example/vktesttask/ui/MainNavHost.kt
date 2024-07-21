package com.example.vktesttask.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.common.constants.FeatureNavigationName
import com.example.currency_converter.presentation.navigation.currencyConverterNavGraph

@Composable
fun MainNavHost(snackbarHostState: SnackbarHostState) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FeatureNavigationName.CURRENCY_CONVERTER.value,
        modifier = Modifier.fillMaxSize()
    ) {
        currencyConverterNavGraph(navController, snackbarHostState)
    }
}