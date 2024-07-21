package com.example.currencycalculator.ui.presentation

sealed class MainState {

    data object Default: MainState()

    data object LeftOpen: MainState()
}