package com.example.currencycalculator.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.currencycalculator.domain.entity.CurrencyInfo

@Composable
fun DropdownMenuItem(
    currencyInfo: CurrencyInfo,
    onMenuItemClick: (CurrencyInfo) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onMenuItemClick(currencyInfo) }
    ) {
        Text(
            text = currencyInfo.code,
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun DropdownMenuItemUp(
    currencyInfo: CurrencyInfo,
    onStartMenuItemClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onStartMenuItemClick()
        }
    ) {

        Text(
            text = currencyInfo.code,
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null)
    }
}