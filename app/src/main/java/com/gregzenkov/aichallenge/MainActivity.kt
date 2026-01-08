package com.gregzenkov.aichallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.gregzenkov.aichallenge.component.RootComponent
import com.gregzenkov.aichallenge.features.home.api.HomeScreen
import com.gregzenkov.aichallenge.ui.theme.AIChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val root = RootComponent.create(
            componentContext = defaultComponentContext()
        )

        setContent {
            AIChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Children(
                        modifier = Modifier.fillMaxSize(),
                        stack = root.childSTack
                    ) {
                        when (val child = it.instance) {
                            is RootComponent.Child.Home -> HomeScreen(
                                component = child.component
                            )
                        }
                    }
                }
            }
        }
    }
}