package com.example.currencycalculator.ui.theme

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.currencycalculator.ui.presentation.AnswerState
import com.example.currencycalculator.ui.presentation.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val state by viewModel.state.collectAsState()
    viewModel.loadItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("Recomposition", "MainScreen")
        CurrencyBox(
            viewModel = viewModel,
            items = state.menuItems,
            state.selectedItemInput,
            state.expandedInput,
            state.selectedItemOutput,
            state.expandedOutput
        )
        Button(
            onClick = {
                viewModel.convertCurrencies()
            },

            ) {
            Text(text = "Конвертировать")
        }
        AnswerText(state = state.answer)
    }

}

@Composable
fun AnswerText(state: AnswerState) {
    when (state) {
        is AnswerState.Answer -> {
            Text(text = "Результат - ${state.price} ${state.title}", color = MaterialTheme.colorScheme.onBackground)
        }

        AnswerState.Initial -> {}
    }
}


@Composable
fun DecimalTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface
) {
    val decimalRegex = Regex("^\\d*\\.?\\d*\$")

    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (decimalRegex.matches(newValue)) {
                onValueChange(newValue)
            }
        },
        modifier = modifier
            .width(200.dp),
        textStyle = LocalTextStyle.current.copy(color = textColor),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        visualTransformation = VisualTransformation.None,
        label = { Text(text = "Значение") }
    )
}


