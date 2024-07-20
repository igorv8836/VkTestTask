package com.example.currency_converter.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.common.constants.FeatureNavigationName
import com.example.currency_converter.presentation.screen.CurrencyConverterScreen


fun NavGraphBuilder.currencyConverterNavGraph(navController: NavHostController) {
    composable(FeatureNavigationName.CURRENCY_CONVERTER.value) {
        CurrencyConverterScreen()
    }
}