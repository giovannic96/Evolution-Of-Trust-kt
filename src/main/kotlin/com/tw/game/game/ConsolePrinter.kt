package com.tw.game.game

import com.tw.game.player.Player

interface ConsolePrinter {

    fun displayScore(player1: Player, player2: Player)
    fun displayWinner(player: Player?)
}
