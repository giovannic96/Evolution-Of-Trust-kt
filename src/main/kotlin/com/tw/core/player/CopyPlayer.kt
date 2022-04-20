package com.tw.core.player

class CopyPlayer(
    name: String,
    score: Int,
) : AbstractPlayer(name, score) {

    private var nextAction: Action = Action.COOPERATE

    override fun doAction(): Action {
        return nextAction
    }

    override fun updateScore(amount: Int) {
        nextAction = calculateNextAction(amount)
        super.updateScore(amount)
    }

    private fun calculateNextAction(roundScore: Int): Action {
        return super.determineOpponentLastActionByRoundScore(roundScore) ?: Action.COOPERATE
    }
}
