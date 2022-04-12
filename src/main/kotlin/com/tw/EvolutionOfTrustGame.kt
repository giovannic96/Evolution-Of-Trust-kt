package com.tw
import com.tw.game.game.GameEngine
import com.tw.game.player.CheatPlayer
import com.tw.game.player.ConsolePlayer
import com.tw.game.player.CoolPlayer
import com.tw.peripheral.ActionReaderImpl
import com.tw.peripheral.ConsolePrinterImpl

fun main(args: Array<String>) {
    val gameEngine = GameEngine()
    val consolePrinter = ConsolePrinterImpl()
    val (player1, player2) = getCoolPlayerAndCheatPlayer()
    val machine = Machine(gameEngine, player1, player2, consolePrinter)

    machine.playGame(3)
}

private fun getConsolePlayers(): Pair<ConsolePlayer, ConsolePlayer> {
    val player1 = ConsolePlayer("p1", 0, ActionReaderImpl())
    val player2 = ConsolePlayer("p2", 0, ActionReaderImpl())

    return Pair(player1, player2)
}

private fun getCoolPlayerAndCheatPlayer(): Pair<CoolPlayer, CheatPlayer> {
    val player1 = CoolPlayer("p1", 0)
    val player2 = CheatPlayer("p2", 0)

    return Pair(player1, player2)
}