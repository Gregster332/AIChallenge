package com.gregzenkov.aichallenge.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

/**
 * Интерфейс компонента счетчика
 */
interface CounterComponent {
    val count: Value<Int>

    fun onIncrementClick()
    fun onDecrementClick()
}

/**
 * Реализация компонента счетчика
 */
class DefaultCounterComponent(
    componentContext: ComponentContext
) : CounterComponent, ComponentContext by componentContext {

    private val _count = MutableValue(0)
    override val count: Value<Int> = _count

    override fun onIncrementClick() {
        _count.value++
    }

    override fun onDecrementClick() {
        _count.value--
    }
}
