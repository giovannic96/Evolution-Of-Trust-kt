package com.tw.core.player

class LastActionWrapper internal constructor(private var lastAction: Action? = null) {

    fun getLastAction(): Action? {
        return this.lastAction
    }

    fun setLastAction(action: Action) {
        this.lastAction = action
    }
}