package com.tw.core.player

class GrudgePlayer(
    name: String,
    score: Int,
    private val lastActionWrapper: LastActionWrapper,
) :
    AbstractPlayer(name, score) {

    private var keepCheating = false
    private var firstTime = true

    override fun doAction(): Action {
        val lastAction = lastActionWrapper.getLastAction() ?: Action.COOPERATE

        if(!keepCheating) {
            keepCheating = lastAction == Action.CHEAT
        }

        val action = if(firstTime) {
            firstTime = false
            Action.COOPERATE
        }
        else if(keepCheating)
            Action.CHEAT
        else
            Action.COOPERATE

        lastActionWrapper.setLastAction(action)
        return action
    }
}
