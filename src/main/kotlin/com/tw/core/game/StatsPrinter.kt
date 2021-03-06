package com.tw.core.game

import com.tw.core.player.Player

interface StatsPrinter {

    fun displayScore(player1: Player, player2: Player)
    fun displayWinner(player: Player?)
}
