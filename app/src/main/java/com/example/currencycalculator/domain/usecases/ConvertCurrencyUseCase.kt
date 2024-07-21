package com.example.currencycalculator.domain.usecases

import com.example.currencycalculator.domain.entity.CurrencyInfo
import com.example.currencycalculator.domain.repository.Repository
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(current: CurrencyInfo, target: CurrencyInfo) =
        repository.convertCurrency(current, target)
}