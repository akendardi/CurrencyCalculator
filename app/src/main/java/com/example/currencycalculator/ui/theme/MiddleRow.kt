package com.example.currencycalculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.currencycalculator.R

@Composable
fun MiddleRow(swapClickListener: () -> Unit) {

    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
        }

        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer)) {
            IconButton(
                onClick = { swapClickListener() },
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(2.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.arrows), contentDescription = null)
            }
        }


    }
}