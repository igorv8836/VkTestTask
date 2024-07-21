package com.example.currency_converter.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.common.constants.FeatureNavigationName
import com.example.currency_converter.presentation.screen.CurrencyConverterScreen
import com.example.currency_converter.presentation.viewmodel.CurrencyConverterViewModel
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.currencyConverterNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    composable(FeatureNavigationName.CURRENCY_CONVERTER.value) {
        val viewModel: CurrencyConverterViewModel = koinViewModel()
        CurrencyConverterScreen(viewModel, snackbarHostState)
    }
}