package com.gregzenkov.aichallenge.features.home.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.gregzenkov.aichallenge.features.home.internal.DefaultHomeComponent

interface HomeComponent {
    companion object {
        fun create(componentContext: ComponentContext): HomeComponent {
            return DefaultHomeComponent(
                componentContext = componentContext
            )
        }
    }
}

@Composable
fun HomeScreen(component: HomeComponent) {

}