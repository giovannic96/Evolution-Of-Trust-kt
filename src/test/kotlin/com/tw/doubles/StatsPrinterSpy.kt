package com.tw.doubles

import com.tw.core.game.StatsPrinter
import com.tw.core.player.Player

class StatsPrinterSpy : StatsPrinter {
    var displayScoreCounter = 0
    var displayWinnerCounter = 0
    var winnerPlayer: Player? = null
    private val roundScores = mutableListOf<Pair<Int, Int>>()

    override fun displayScore(player1: Player, player2: Player) {
        displayScoreCounter++
        roundScores.add(Pair(player1.getScore(), player2.getScore()))
    }

    override fun displayWinner(player: Player?) {
        displayWinnerCounter++
        winnerPlayer = player
    }

    fun getRoundScores(): List<Pair<Int,Int>> {
        return roundScores
    }
}