package com.example.currency_converter.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currency_converter.R
import com.example.currency_converter.presentation.screen.ui_items.CurrencySumTextField
import com.example.currency_converter.presentation.screen.ui_items.CurrencyTextField
import com.example.currency_converter.presentation.screen.ui_items.TextFieldGroup
import com.example.ui_theme.app_theme.AppTheme

@Composable
internal fun CurrencyConverterScreen() {

    val fromCurrencyText = remember { mutableStateOf("") }
    val toCurrencyText = remember { mutableStateOf("") }
    val fromCurrencyExpanded = remember { mutableStateOf(false) }
    val toCurrencyExpanded = remember { mutableStateOf(false) }
    val suggestions by remember { mutableStateOf(emptyList<String>()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldGroup(
            textField1 = {
                CurrencyTextField(
                    text = fromCurrencyText,
                    hint = "Исходная валюта",
                    trailingIconId = R.drawable.change,
                    expanded = fromCurrencyExpanded,
                    suggestions = suggestions,
                    getFilteredCurrencies = {}
                ) {

                }
            },
            textField2 = {
                CurrencyTextField(
                    text = toCurrencyText,
                    hint = "Целевая валюта",
                    trailingIconId = null,
                    expanded = toCurrencyExpanded,
                    suggestions = suggestions,
                    getFilteredCurrencies = {}
                ) {}
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextFieldGroup(
            textField1 = {
                CurrencySumTextField(
                    text = fromCurrencyText,
                    hint = "Введите количество",
                    readOnly = false,
                ) {

                }
            },
            textField2 = {
                CurrencySumTextField(
                    text = toCurrencyText,
                    hint = "Результат конвертирования",
                    readOnly = true
                ) {}
            }
        )

        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 16.dp)
        ){
            Button(
                onClick = {  },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
            ) {
                Text(text = "Конвертировать")
            }
        }


    }

}

@Composable
@Preview
internal fun CurrencyConverterScreenPreview() {
    AppTheme {
        CurrencyConverterScreen()
    }
}