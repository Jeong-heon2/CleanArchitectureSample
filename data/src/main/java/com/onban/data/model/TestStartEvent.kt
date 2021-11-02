package com.onban.data.model

import com.onban.domain.model.StartEvent

data class TestStartEvent(
    private var _name: String,
) : StartEvent {
    override val name: String
        get() = _name

    fun setName(name: String) {
        _name = name
    }
}
fun TestStartEvent.validateName(): Boolean {
    if (name.length > 10) {
        return false
    }
    return true
}
