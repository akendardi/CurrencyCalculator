package com.example.currencycalculator.ui.presentation

import com.example.currencycalculator.domain.entity.CurrencyInfo

data class ViewModelState(
    val menuItems: List<CurrencyInfo> = emptyList(),
    val expandedInput: Boolean = false,
    val expandedOutput: Boolean = false,
    val selectedItemInput: CurrencyInfo = CurrencyInfo(),
    val selectedItemOutput: CurrencyInfo = CurrencyInfo(),
    val answer: AnswerState = AnswerState.Initial,
    val count: Double = 0.0
)
