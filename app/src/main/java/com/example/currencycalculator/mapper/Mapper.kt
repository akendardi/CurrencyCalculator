package com.example.currencycalculator.mapper

import com.example.currencycalculator.data.CurrencyInfoDto
import com.example.currencycalculator.domain.entity.CurrencyInfo
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapDtoToEntity(currencyInfoDto: CurrencyInfoDto): CurrencyInfo {
        return CurrencyInfo(code = currencyInfoDto.code, value = currencyInfoDto.value)
    }
}