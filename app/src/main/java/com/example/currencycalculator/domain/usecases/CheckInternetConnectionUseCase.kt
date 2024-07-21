package com.example.currencycalculator.domain.usecases

import com.example.currencycalculator.domain.repository.Repository
import javax.inject.Inject

class CheckInternetConnectionUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() = repository.checkInternetConnection()
}