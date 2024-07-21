package com.example.currencycalculator.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currencycalculator.domain.entity.CurrencyInfo
import com.example.currencycalculator.ui.presentation.MainViewModel

@Composable
fun CurrencyBox(
    viewModel: MainViewModel,
    items: List<CurrencyInfo>,
    selectedItemInput: CurrencyInfo,
    expandedInput: Boolean,
    selectedItemOutput: CurrencyInfo,
    expandedOutput: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.colorScheme.primaryContainer)

    ) {
        Log.d("Recomposition", "Card")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Текущая валюта", modifier = Modifier.padding(top = 15.dp))
            CurrencyRow(
                items = items,
                expanded = expandedInput,
                currentItem = selectedItemInput,
                onStartMenuItemClick = {
                    viewModel.changeUpMenuState()
                },
                onMenuItemClick = {
                    viewModel.setUpCurrentElement(it)
                    viewModel.closeUpMenu()
                },
                closeMenu = {
                    viewModel.closeUpMenu()
                },
                isTop = true,
                textValueChanged = {
                    viewModel.changeCount(it)
                }
            )
            MiddleRow(swapClickListener = {
                viewModel.swapCurrencies()
            })

            Text(text = "Целевая валюта")

            CurrencyRow(
                items = items,
                expanded = expandedOutput,
                currentItem = selectedItemOutput,
                onStartMenuItemClick = {
                    viewModel.changeDownMenuState()
                },
                onMenuItemClick = {
                    viewModel.setDownCurrentElement(it)
                    viewModel.closeDownMenu()
                },
                closeMenu = {
                    viewModel.closeDownMenu()
                },
                modifier = Modifier.padding(bottom = 20.dp),
                isTop = false,
                textValueChanged = {}
            )
        }
    }
}