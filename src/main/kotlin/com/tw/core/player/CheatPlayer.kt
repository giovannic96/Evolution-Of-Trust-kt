package com.tw.core.player

class CheatPlayer(
    name: String,
    score: Int,
    private val lastActionWrapper: LastActionWrapper,
) :
    AbstractPlayer(name, score) {

    override fun doAction(): Action {
        val action = Action.CHEAT
        lastActionWrapper.setLastAction(action)
        return action
    }
}
