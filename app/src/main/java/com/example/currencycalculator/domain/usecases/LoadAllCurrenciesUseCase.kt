package com.example.currencycalculator.domain.usecases

import com.example.currencycalculator.domain.repository.Repository
import javax.inject.Inject

class LoadAllCurrenciesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.loadAllCurrencies()
}