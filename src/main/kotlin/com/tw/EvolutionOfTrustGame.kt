package com.tw
import com.tw.core.game.GameEngine
import com.tw.core.game.Machine
import com.tw.core.player.CheatPlayer
import com.tw.core.player.ChoicePlayer
import com.tw.core.player.CoolPlayer
import com.tw.io.ActionReaderImpl
import com.tw.io.ConsolePrinterImpl

fun main(args: Array<String>) {
    val gameEngine = GameEngine()
    val consolePrinter = ConsolePrinterImpl()
    val (player1, player2) = getCoolPlayerAndCheatPlayer()
    val machine = Machine(gameEngine, player1, player2, consolePrinter)

    machine.playGame(3)
}

private fun getConsolePlayers(): Pair<ChoicePlayer, ChoicePlayer> {
    val player1 = ChoicePlayer("p1", 0, ActionReaderImpl())
    val player2 = ChoicePlayer("p2", 0, ActionReaderImpl())

    return Pair(player1, player2)
}

private fun getCoolPlayerAndCheatPlayer(): Pair<CoolPlayer, CheatPlayer> {
    val player1 = CoolPlayer("p1", 0)
    val player2 = CheatPlayer("p2", 0)

    return Pair(player1, player2)
}