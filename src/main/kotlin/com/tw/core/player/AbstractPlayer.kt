package com.tw.core.player

import com.tw.core.action.Action

abstract class AbstractPlayer(
    private val name: String,
    private var score: Int,
) : Player {

    override fun getName(): String {
        return this.name
    }

    override fun getScore(): Int {
        return this.score
    }

    override fun updateScore(amount: Int) {
        this.score += amount
    }

    override fun determineOpponentLastActionByRoundScore(roundScore: Int): Action? {
        // TODO: better to move this logic inside Rules
        return when (roundScore) {
            0, -1 -> Action.CHEAT
            2, 3 -> Action.COOPERATE
            else -> null
        }
    }
}