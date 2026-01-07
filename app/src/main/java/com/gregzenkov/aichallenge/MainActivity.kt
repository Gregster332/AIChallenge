package com.gregzenkov.aichallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.gregzenkov.aichallenge.component.DefaultPageNavigationComponent
import com.gregzenkov.aichallenge.ui.PageNavigationContent
import com.gregzenkov.aichallenge.ui.theme.AIChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Создаем корневой компонент с постраничной навигацией
        val pageNavigationComponent = DefaultPageNavigationComponent(
            componentContext = defaultComponentContext()
        )

        setContent {
            AIChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PageNavigationContent(
                        component = pageNavigationComponent,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}