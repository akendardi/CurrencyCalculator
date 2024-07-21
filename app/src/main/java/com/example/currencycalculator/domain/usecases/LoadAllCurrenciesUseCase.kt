package com.example.currencycalculator.domain.usecases

import com.example.currencycalculator.domain.repository.Repository
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject
class LoadAllCurrenciesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.loadAllCurrencies()
}