package com.tw.core.player

import java.util.*

class DetectivePlayer(
    name: String,
    score: Int,
    private val lastActionWrapper: LastActionWrapper,
) :
    AbstractPlayer(name, score) {

    private var otherPlayerHasCheated = false
    private var predefinedActions: Queue<Action> =
        LinkedList(listOf(Action.COOPERATE, Action.CHEAT, Action.COOPERATE, Action.COOPERATE))

    override fun doAction(): Action {
        val lastAction = lastActionWrapper.getLastAction() ?: Action.COOPERATE

        if (!otherPlayerHasCheated && predefinedActions.isNotEmpty())
            otherPlayerHasCheated = lastAction == Action.CHEAT

        val action = if (predefinedActions.isNotEmpty()) {
            predefinedActions.poll()
        } else if (otherPlayerHasCheated) {
            lastAction
        } else {
            Action.CHEAT
        }

        lastActionWrapper.setLastAction(action)
        return action
    }
}

