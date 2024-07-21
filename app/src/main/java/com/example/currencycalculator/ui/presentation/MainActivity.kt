package com.example.currencycalculator.ui.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.currencycalculator.data.ApiService
import com.example.currencycalculator.ui.theme.CurrencyCalculatorTheme
import com.example.currencycalculator.ui.theme.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CurrencyCalculatorTheme {
                if (!viewModel.checkIntertnetConnection()) {
                    Toast.makeText(
                        this,
                        "Отсутсвует подключение к интернету. Проверьте подключение и перезапустите приложение",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    MainScreen(viewModel)
                }

            }
        }
    }
}
