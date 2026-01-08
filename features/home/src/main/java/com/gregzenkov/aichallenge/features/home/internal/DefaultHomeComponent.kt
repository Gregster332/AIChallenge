package com.gregzenkov.aichallenge.features.home.internal

import com.arkivanov.decompose.ComponentContext
import com.gregzenkov.aichallenge.features.home.api.HomeComponent

internal class DefaultHomeComponent(
    componentContext: ComponentContext
) : HomeComponent, ComponentContext by componentContext {

}