package com.tw.core.game

import com.tw.core.player.Player

class Machine(
    private val gameEngine: GameEngine,
    private val player1: Player,
    private val player2: Player,
    private val consolePrinter: ConsolePrinter,
) {

    fun playGame(rounds: Int) {
        for (i in 1..rounds) {
            val player1Action = player1.doAction()
            val player2Action = player2.doAction()

            val points = gameEngine.calculatePointsForActions(player1Action, player2Action)

            player1.updateScore(points.player1Points)
            player2.updateScore(points.player2Points)

            consolePrinter.displayScore(player1, player2)
        }

        val winner = gameEngine.calculateWinner(player1, player2)
        consolePrinter.displayWinner(winner)
    }
}