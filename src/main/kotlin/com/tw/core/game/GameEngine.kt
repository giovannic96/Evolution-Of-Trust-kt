package com.tw.core.game

import com.tw.core.action.Action
import com.tw.core.player.Player

class GameEngine(private val rules: Rules = Rules()) {

    fun calculateWinner(player1: Player, player2: Player): Player? {
        val winner = if (player1.getScore() > player2.getScore()) {
            player1
        } else if (player1.getScore() < player2.getScore()) {
            player2
        } else {
            null
        }
        return winner
    }

    fun calculatePointsForActions(player1Action: Action, player2Action: Action): Points {
        val actions = Pair(player1Action, player2Action)
        return rules.applyRules(actions)
    }
}
