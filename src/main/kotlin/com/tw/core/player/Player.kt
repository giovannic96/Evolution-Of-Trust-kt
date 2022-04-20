package com.tw.core.player

interface Player {

    fun getName(): String
    fun getScore(): Int
    fun doAction(): Action
    fun updateScore(amount: Int)
    fun determineOpponentLastActionByRoundScore(roundScore: Int): Action?
}
