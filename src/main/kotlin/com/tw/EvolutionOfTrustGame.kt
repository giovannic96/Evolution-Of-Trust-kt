package com.tw

import com.tw.core.game.GameEngine
import com.tw.core.game.Machine
import com.tw.core.player.*
import com.tw.io.ActionReaderImpl
import com.tw.io.StatsPrinterImpl

fun main(args: Array<String>) {
    val gameEngine = GameEngine()
    val consolePrinter = StatsPrinterImpl()
    val (player1, player2) = createCopyPlayerAndDetectivePlayer()
    val machine = Machine(gameEngine, player1, player2, consolePrinter)

    machine.playGame(5)
}

private fun createConsolePlayers(): Pair<ChoicePlayer, ChoicePlayer> {
    val lastActionWrapper = LastActionWrapper()
    val player1 = ChoicePlayer("choice_player1", 0, lastActionWrapper, ActionReaderImpl())
    val player2 = ChoicePlayer("choice_player2", 0, lastActionWrapper, ActionReaderImpl())

    return Pair(player1, player2)
}

private fun createCoolPlayerAndCheatPlayer(): Pair<CoolPlayer, CheatPlayer> {
    val lastActionWrapper = LastActionWrapper()
    val player1 = CoolPlayer("cool_player", 0, lastActionWrapper)
    val player2 = CheatPlayer("cheat_player", 0, lastActionWrapper)

    return Pair(player1, player2)
}

private fun createCopyPlayerAndCheatPlayer(): Pair<CopyPlayer, CheatPlayer> {
    val lastActionWrapper = LastActionWrapper()
    val player1 = CopyPlayer("copy_player", 0, lastActionWrapper)
    val player2 = CheatPlayer("cheat_player", 0, lastActionWrapper)

    return Pair(player1, player2)
}

private fun createCopyPlayerAndGrudgePlayer(): Pair<CopyPlayer, GrudgePlayer> {
    val lastActionWrapper = LastActionWrapper()
    val player1 = CopyPlayer("copy_player", 0, lastActionWrapper)
    val player2 = GrudgePlayer("grudge_player", 0, lastActionWrapper)

    return Pair(player1, player2)
}

private fun createCopyPlayerAndDetectivePlayer(): Pair<CopyPlayer, DetectivePlayer> {
    val lastActionWrapper = LastActionWrapper()
    val player1 = CopyPlayer("copy_player", 0, lastActionWrapper)
    val player2 = DetectivePlayer("detective_player", 0, lastActionWrapper)

    return Pair(player1, player2)
}