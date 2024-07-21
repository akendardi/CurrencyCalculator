package com.example.currencycalculator.domain.repository

import com.example.currencycalculator.domain.entity.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun loadAllCurrencies(): Flow<List<CurrencyInfo>>

    fun checkInternetConnection(): Boolean


    suspend fun convertCurrency(current: CurrencyInfo, target: CurrencyInfo): Flow<Double>

}