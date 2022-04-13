package com.tw.core.player

class CoolPlayer(
    name: String,
    score: Int
) :
    AbstractPlayer(name, score) {

    override fun doAction(): Action {
        return Action.COOPERATE
    }
}
