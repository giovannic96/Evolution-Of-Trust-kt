package com.tw.core.game

import com.tw.core.player.Action

class Rules {
    fun applyRules(actions: Pair<Action, Action>): Points {
        val (player1Action, player2Action) = actions

        val player1Cheated = player1Action == Action.CHEAT
        val player1Cooperated = player1Action == Action.COOPERATE
        val player2Cheated = player2Action == Action.CHEAT
        val player2Cooperated = player2Action == Action.COOPERATE

        return when {
            player1Cheated && player2Cheated -> Points(0, 0)
            player1Cheated && player2Cooperated -> Points(3, -1)
            player1Cooperated && player2Cheated -> Points(-1, 3)
            else -> Points(2, 2)
        }
    }
}
