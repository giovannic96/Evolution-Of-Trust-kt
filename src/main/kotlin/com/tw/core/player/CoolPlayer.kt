package com.tw.core.player

import com.tw.core.action.Action

class CoolPlayer(
    name: String,
    score: Int,
) :
    AbstractPlayer(name, score) {

    override fun doAction(): Action {
        return Action.COOPERATE
    }
}
