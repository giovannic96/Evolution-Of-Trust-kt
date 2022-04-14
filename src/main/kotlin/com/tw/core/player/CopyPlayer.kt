package com.tw.core.player

class CopyPlayer(
    name: String,
    score: Int,
    private val lastActionWrapper: LastActionWrapper,
) :
    AbstractPlayer(name, score) {

    private var firstTime = true

    override fun doAction(): Action {
        val lastAction = lastActionWrapper.getLastAction() ?: Action.COOPERATE

        val action = if(firstTime)
            Action.COOPERATE
        else
            lastAction

        firstTime = false
        lastActionWrapper.setLastAction(action)
        return action
    }
}
