package com.tw.io

import com.tw.core.game.StatsPrinter
import com.tw.core.player.Player

class StatsPrinterImpl : StatsPrinter {

    override fun displayScore(player1: Player, player2: Player) {
        println()
        println("[${player1.getName()}] Score: ${player1.getScore()}")
        println("[${player2.getName()}] Score: ${player2.getScore()}")
        println()
    }

    override fun displayWinner(player: Player?) {
        println()
        if (player == null)
            println("The game ended in a draw!")
        else
            println("The winner is ${player.getName()}!")
    }
}