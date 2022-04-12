package com.tw.game.player

class CheatPlayer(
    name: String,
    score: Int
) :
    AbstractPlayer(name, score) {

    override fun doAction(): Action {
        return Action.CHEAT
    }
}
