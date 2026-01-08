package com.gregzenkov.aichallenge.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.gregzenkov.aichallenge.features.home.api.HomeComponent
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

interface RootComponent {
    val childSTack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
    }

    companion object {
        fun create(componentContext: ComponentContext): RootComponent {
            return DefaultRootComponent(
                componentContext = componentContext
            )
        }
    }
}

private class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val stackNavigation = StackNavigation<Config>()
    override val childSTack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = stackNavigation,
        serializer = Config.serializer(),
        handleBackButton = true,
        initialStack = { listOf(Config.Home) },
        childFactory = ::child
    )

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            is Config.Home -> RootComponent.Child.Home(
                component = HomeComponent.create(
                    componentContext = componentContext
                )
            )
        }
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Home : Config
    }
}