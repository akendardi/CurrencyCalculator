package com.example.currencycalculator.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.currencycalculator.domain.entity.CurrencyInfo
import com.example.currencycalculator.domain.repository.Repository
import com.example.currencycalculator.mapper.Mapper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper,
    private val context: Context
) : Repository {

    private var currenciesValues: CurrencyResponse? = null

    override suspend fun loadAllCurrencies(): Flow<List<CurrencyInfo>> =
        flow {
            val response = apiService.loadCurrencies()
            currenciesValues = response
            Log.d("loadAllCurrenciesUseCase", response.toString())
            val listCurrencyInfo = response.data.values.toList().map { mapper.mapDtoToEntity(it) }
            emit(listCurrencyInfo)
        }.flowOn(Dispatchers.IO)

    override fun checkInternetConnection(): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }


    override suspend fun convertCurrency(
        current: CurrencyInfo,
        target: CurrencyInfo
    ): Flow<Double> = flow {
        if (currenciesValues == null) {
            currenciesValues = apiService.loadCurrencies()
        }

        val usdInCurr = currenciesValues?.data?.get(current.code)?.value
            ?: error("Currency not found: ${current.code}")
        val usdInTar = currenciesValues?.data?.get(target.code)?.value
            ?: error("Currency not found: ${target.code}")

        if (usdInCurr == 0.0) error("Currency value for ${current.code} is zero")
        if (usdInTar == 0.0) error("Currency value for ${target.code} is zero")

        val result = usdInTar / usdInCurr
        emit(result)
    }.flowOn(Dispatchers.Default)

}