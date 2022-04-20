package com.tw.core.player

class GrudgePlayer(
    name: String,
    score: Int,
) :
    AbstractPlayer(name, score) {

    private var nextAction: Action = Action.COOPERATE
    private var keepCheating = false

    override fun doAction(): Action {
        return nextAction
    }

    override fun updateScore(amount: Int) {
        nextAction = calculateNextAction(amount)
        super.updateScore(amount)
    }

    private fun calculateNextAction(roundScore: Int): Action {
        val opponentLastAction: Action = super.determineOpponentLastActionByRoundScore(roundScore)
            ?: Action.COOPERATE

        if (!keepCheating) {
            keepCheating = opponentLastAction == Action.CHEAT
        }

        return if (keepCheating)
            Action.CHEAT
        else
            Action.COOPERATE
    }
}
