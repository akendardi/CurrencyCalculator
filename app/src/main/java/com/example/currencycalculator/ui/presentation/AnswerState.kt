package com.example.currencycalculator.ui.presentation

sealed class AnswerState {

    data object Initial: AnswerState()

    data class Answer(val price: Double, val title: String) : AnswerState()
}