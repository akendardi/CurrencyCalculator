package com.example.currencycalculator.data

import com.google.gson.annotations.SerializedName

data class CurrencyInfoDto(
    @SerializedName("code")
    val code: String,
    @SerializedName("value")
    val value: Double,
)