package com.example.currencycalculator.data

import retrofit2.http.GET

interface ApiService {

    @GET("latest")
    suspend fun loadCurrencies(): CurrencyResponse

}