package com.example.currency_converter.presentation.screen.ui_items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
internal fun CurrencySumTextField(
    text: MutableState<String>,
    readOnly: Boolean,
    hint: String,
    setSum: (String) -> Unit
) {
    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
            setSum(it)
        },
        placeholder = {
            Text(
                text = hint
            )
        },
        readOnly = readOnly,
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors()
            .copy(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
    )
}