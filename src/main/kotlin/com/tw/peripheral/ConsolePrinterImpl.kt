package com.tw.peripheral

import com.tw.game.game.ConsolePrinter
import com.tw.game.player.Player

class ConsolePrinterImpl : ConsolePrinter {

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