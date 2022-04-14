package com.tw.core.player

class CoolPlayer(
    name: String,
    score: Int,
    private val lastActionWrapper: LastActionWrapper,
) :
    AbstractPlayer(name, score) {

    override fun doAction(): Action {
        val action = Action.COOPERATE
        lastActionWrapper.setLastAction(action)
        return action
    }
}
