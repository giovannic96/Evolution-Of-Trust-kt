package com.tw.doubles

import com.tw.core.game.ConsolePrinter
import com.tw.core.player.Player

class ConsolePrinterSpy : ConsolePrinter {
    var displayScoreCounter = 0
    var displayWinnerCounter = 0
    var winnerPlayer: Player? = null

    override fun displayScore(player1: Player, player2: Player) {
        displayScoreCounter++
    }

    override fun displayWinner(player: Player?) {
        displayWinnerCounter++
        winnerPlayer = player
    }
}