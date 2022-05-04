package com.tw.core.game

import com.tw.core.action.Action

private const val ZERO_POINTS = 0
private const val WIN_POINTS = 3
private const val LOOSE_POINTS = -1
private const val DRAW_POINTS = 2

class Rules {
    fun applyRules(actions: Pair<Action, Action>): Points {
        val (player1Action, player2Action) = actions

        val player1Cheated = player1Action == Action.CHEAT
        val player1Cooperated = player1Action == Action.COOPERATE
        val player2Cheated = player2Action == Action.CHEAT
        val player2Cooperated = player2Action == Action.COOPERATE

        return when {
            player1Cheated && player2Cheated -> Points(ZERO_POINTS, ZERO_POINTS)
            player1Cheated && player2Cooperated -> Points(WIN_POINTS, LOOSE_POINTS)
            player1Cooperated && player2Cheated -> Points(LOOSE_POINTS, WIN_POINTS)
            else -> Points(DRAW_POINTS, DRAW_POINTS)
        }
    }
}
