package com.tw.game.game

import com.tw.game.player.Action

class Rules {
    fun applyRules(actions: Pair<Action, Action>): Points {
        val (player1Action, player2Action) = actions

        return when {
            player1Action == Action.CHEAT && player2Action == Action.CHEAT -> Points(0, 0)
            player1Action == Action.CHEAT && player2Action == Action.COOPERATE -> Points(3, -1)
            player1Action == Action.COOPERATE && player2Action == Action.CHEAT -> Points(-1, 3)
            else -> Points(2, 2)
        }
    }
}
