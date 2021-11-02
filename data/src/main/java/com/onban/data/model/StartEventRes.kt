package com.onban.data.model

import com.google.gson.annotations.SerializedName
import com.onban.domain.model.StartEvent

data class StartEventRes(
    @SerializedName("title") val _name: String,
) : StartEvent {
    override val name: String
        get() = _name
}
