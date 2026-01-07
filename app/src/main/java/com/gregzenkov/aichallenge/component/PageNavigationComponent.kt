package com.gregzenkov.aichallenge.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

/**
 * Интерфейс компонента постраничной навигации
 */
interface PageNavigationComponent {
    val pages: Value<ChildPages<*, Page>>

    fun selectPage(index: Int)

    /**
     * Запечатанный класс для представления страниц
     */
    sealed class Page {
        data class Home(val component: HomePageComponent) : Page()
        data class Profile(val component: ProfilePageComponent) : Page()
        data class Settings(val component: SettingsPageComponent) : Page()
    }
}

/**
 * Реализация компонента постраничной навигации
 */
class DefaultPageNavigationComponent(
    componentContext: ComponentContext
) : PageNavigationComponent, ComponentContext by componentContext {

    private val navigation = PagesNavigation<Config>()

    override val pages: Value<ChildPages<*, PageNavigationComponent.Page>> =
        childPages(
            source = navigation,
            serializer = Config.serializer(),
            initialPages = {
                Pages(
                    items = listOf(Config.Home, Config.Profile, Config.Settings),
                    selectedIndex = 0
                )
            },
            childFactory = ::createChild
        )

    override fun selectPage(index: Int) {
        navigation.select(index)
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): PageNavigationComponent.Page =
        when (config) {
            Config.Home -> PageNavigationComponent.Page.Home(
                DefaultHomePageComponent(componentContext)
            )
            Config.Profile -> PageNavigationComponent.Page.Profile(
                DefaultProfilePageComponent(componentContext)
            )
            Config.Settings -> PageNavigationComponent.Page.Settings(
                DefaultSettingsPageComponent(componentContext)
            )
        }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Home : Config

        @Serializable
        data object Profile : Config

        @Serializable
        data object Settings : Config
    }
}

// Компонент домашней страницы
interface HomePageComponent {
    val title: String
}

class DefaultHomePageComponent(
    componentContext: ComponentContext
) : HomePageComponent, ComponentContext by componentContext {
    override val title: String = "Домашняя страница"
}

// Компонент страницы профиля
interface ProfilePageComponent {
    val title: String
}

class DefaultProfilePageComponent(
    componentContext: ComponentContext
) : ProfilePageComponent, ComponentContext by componentContext {
    override val title: String = "Профиль"
}

// Компонент страницы настроек
interface SettingsPageComponent {
    val title: String
}

class DefaultSettingsPageComponent(
    componentContext: ComponentContext
) : SettingsPageComponent, ComponentContext by componentContext {
    override val title: String = "Настройки"
}
