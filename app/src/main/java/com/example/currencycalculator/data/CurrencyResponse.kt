package com.example.currencycalculator.data

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("data")
    val data: Map<String, CurrencyInfoDto>
)