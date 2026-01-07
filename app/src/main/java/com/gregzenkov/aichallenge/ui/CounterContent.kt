package com.gregzenkov.aichallenge.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.gregzenkov.aichallenge.component.CounterComponent
import com.gregzenkov.aichallenge.ui.theme.AIChallengeTheme

/**
 * UI для компонента счетчика
 */
@Composable
fun CounterContent(
    component: CounterComponent,
    modifier: Modifier = Modifier
) {
    val count by component.count.subscribeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Счетчик: $count",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = component::onDecrementClick) {
                Text("Минус")
            }

            Button(onClick = component::onIncrementClick) {
                Text("Плюс")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterContentPreview() {
    AIChallengeTheme {
        CounterContent(
            component = object : CounterComponent {
                override val count: Value<Int> = MutableValue(5)
                override fun onIncrementClick() {}
                override fun onDecrementClick() {}
            }
        )
    }
}
