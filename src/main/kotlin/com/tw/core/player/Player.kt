package com.tw.core.player

import com.tw.core.action.Action

interface Player {

    fun getName(): String
    fun getScore(): Int
    fun doAction(): Action
    fun updateScore(amount: Int)
    fun determineOpponentLastActionByRoundScore(roundScore: Int): Action?
}
