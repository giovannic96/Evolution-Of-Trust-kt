package com.tw.core.player

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

        opponentHasCheated = hasOpponentCheated(opponentLastAction)

        return if (predefinedActions.isNotEmpty()) {
            predefinedActions.poll()
        } else if (opponentHasCheated) {
            opponentLastAction
        } else {
            Action.CHEAT
        }
    }

    private fun hasOpponentCheated(opponentLastAction: Action): Boolean {
        return if (!opponentHasCheated && predefinedActions.isNotEmpty())
            opponentLastAction == Action.CHEAT
        else
            false
    }
}

