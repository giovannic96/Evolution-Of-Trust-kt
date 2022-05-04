package com.tw.core.player

import com.tw.core.action.Action

class CheatPlayer(
    name: String,
    score: Int,
) :
    AbstractPlayer(name, score) {

    override fun doAction(): Action {
        return Action.CHEAT
    }
}
