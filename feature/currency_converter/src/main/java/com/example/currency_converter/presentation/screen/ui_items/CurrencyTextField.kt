package com.example.currency_converter.presentation.screen.ui_items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
internal fun CurrencyTextField(
    text: MutableState<String>,
    hint: String,
    trailingIconId: Int?,
    expanded: MutableState<Boolean>,
    suggestions: List<String>,
    getFilteredCurrencies: (String) -> Unit,
    suggestionIsSelected: (String) -> Unit,
    trailingIconOnClick: () -> Unit
) {
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
            expanded.value = it.isNotEmpty()
            getFilteredCurrencies(it)
        },
        placeholder = {
            Text(
                text = hint
            )
        },
        trailingIcon = {
            trailingIconId?.let {
                IconButton(onClick = trailingIconOnClick) {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null
                    )
                }
            }

        },
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            },
        colors = TextFieldDefaults.colors()
            .copy(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
    )

    if (expanded.value) {
        Box {
            Card(
                modifier = Modifier
                    .width(textFieldSize.width.dp),
                elevation = CardDefaults.cardElevation(15.dp),
                shape = RoundedCornerShape(10.dp)
            ) {

                LazyColumn(
                    modifier = Modifier.heightIn(max = 150.dp),
                ) {
                    items(suggestions) { it ->
                        SuggestedItem(title = it) {
                            suggestionIsSelected(it)
                        }
                    }
                }

            }
        }
    }
}