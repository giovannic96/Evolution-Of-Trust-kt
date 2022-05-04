package com.tw.core.player

import com.tw.core.action.Action
import java.util.*

class DetectivePlayer(
    name: String,
    score: Int,
) :
    AbstractPlayer(name, score) {

    private var nextAction: Action? = null
    private var opponentHasCheated = false
    private var predefinedActions: Queue<Action> =
        LinkedList(listOf(Action.COOPERATE, Action.CHEAT, Action.COOPERATE, Action.COOPERATE))

    override fun doAction(): Action {
        return nextAction ?: predefinedActions.poll()
    }

    override fun updateScore(amount: Int) {
        nextAction = calculateNextAction(amount)
        super.updateScore(amount)
    }

    private fun calculateNextAction(roundScore: Int): Action {
        val opponentLastAction: Action = super.determineOpponentLastActionByRoundScore(roundScore)
            ?: Action.COOPERATE

        determineIfOpponentCheated(opponentLastAction)

        return if (predefinedActions.isNotEmpty()) {
            predefinedActions.poll()
        } else if (opponentHasCheated) {
            opponentLastAction
        } else {
            Action.CHEAT
        }
    }

    private fun determineIfOpponentCheated(opponentLastAction: Action) {
        if (!opponentHasCheated && predefinedActions.isNotEmpty())
            opponentHasCheated = opponentLastAction == Action.CHEAT
    }
}

