package com.example.currencycalculator.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currencycalculator.domain.entity.CurrencyInfo

@Composable
fun CurrencyRow(
    items: List<CurrencyInfo>,
    expanded: Boolean,
    currentItem: CurrencyInfo,
    onStartMenuItemClick: () -> Unit,
    onMenuItemClick: (CurrencyInfo) -> Unit,
    closeMenu: () -> Unit,
    modifier: Modifier = Modifier,
    isTop: Boolean,
    textValueChanged: (String) -> Unit
) {

    Log.d("Recomposition", "CurrencyRow")
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DropdownMenuItemUp(currencyInfo = currentItem) {
                onStartMenuItemClick()
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { closeMenu() },
                modifier = Modifier.height(200.dp)
            ) {
                Log.d("DropdownMenu", expanded.toString())
                items.forEach { currencyItem ->
                    DropdownMenuItem(
                        currencyInfo = currencyItem,
                        onMenuItemClick = { clickedItem ->
                            onMenuItemClick(clickedItem)
                        }
                    )
                }
            }
        }
        var value by remember {
            mutableStateOf("")
        }
        if (isTop) {
            DecimalTextField(value = value, onValueChange = {
                value = it
                textValueChanged(it)

            })
        }
    }
}