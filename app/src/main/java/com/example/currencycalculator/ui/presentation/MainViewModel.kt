package com.example.currencycalculator.ui.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencycalculator.domain.entity.CurrencyInfo
import com.example.currencycalculator.domain.usecases.CheckInternetConnectionUseCase
import com.example.currencycalculator.domain.usecases.ConvertCurrencyUseCase
import com.example.currencycalculator.domain.usecases.LoadAllCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadAllCurrenciesUseCase: LoadAllCurrenciesUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val checkInternetConnectionUseCase: CheckInternetConnectionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    fun checkIntertnetConnection(): Boolean {
        return checkInternetConnectionUseCase.invoke()
    }


    fun loadItems() {
        viewModelScope.launch {
            loadAllCurrenciesUseCase.invoke().collect { currencies ->
                Log.d("ViewModel", currencies.toString())
                _state.value = _state.value.copy(menuItems = currencies)
            }
        }
    }

    fun changeCount(value: String) {
        Log.d("ChangeCount", value)
        val newCount = if (value.isEmpty()) 0.0 else value.toDouble()
        _state.value = _state.value.copy(count = newCount)
    }

    fun changeUpMenuState() {
        _state.value = _state.value.copy(expandedInput = !_state.value.expandedInput)
    }

    fun setUpCurrentElement(currencyInfo: CurrencyInfo) {
        _state.value = _state.value.copy(selectedItemInput = currencyInfo)
    }

    fun closeUpMenu() {
        _state.value = _state.value.copy(expandedInput = false)
    }

    fun changeDownMenuState() {
        _state.value = _state.value.copy(expandedOutput = !_state.value.expandedOutput)
    }

    fun setDownCurrentElement(currencyInfo: CurrencyInfo) {
        _state.value = _state.value.copy(selectedItemOutput = currencyInfo)
    }

    fun closeDownMenu() {
        _state.value = _state.value.copy(expandedOutput = false)
    }

    fun swapCurrencies() {
        val temp = _state.value.selectedItemInput
        _state.value = _state.value.copy(
            selectedItemInput = _state.value.selectedItemOutput,
            selectedItemOutput = temp
        )
    }

    fun convertCurrencies() {
        viewModelScope.launch {
            val current = _state.value.selectedItemInput
            val target = _state.value.selectedItemOutput
            convertCurrencyUseCase.invoke(current, target).collect { conversionResult ->
                Log.d("Convert", "${current.code} в ${target.code} = $conversionResult")
                _state.value = _state.value.copy(
                    answer = AnswerState.Answer(
                        price = conversionResult * _state.value.count,
                        title = target.code
                    )
                )
            }
        }
    }
}
