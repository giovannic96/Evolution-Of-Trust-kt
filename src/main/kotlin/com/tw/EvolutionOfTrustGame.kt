package com.tw

import com.tw.core.game.GameEngine
import com.tw.core.game.Machine
import com.tw.core.player.*
import com.tw.io.ActionReaderImpl
import com.tw.io.StatsPrinterImpl

private const val NUM_ROUNDS = 5

fun main(args: Array<String>) {
    val gameEngine = GameEngine()
    val statsPrinter = StatsPrinterImpl()
    val (player1, player2) = createCopyPlayerAndGrudgePlayer()
    val machine = Machine(gameEngine, player1, player2, statsPrinter)

    machine.playGame(NUM_ROUNDS)
}

private fun createChoicePlayers(): Pair<ChoicePlayer, ChoicePlayer> {
    val player1 = ChoicePlayer("choice_player1", 0, ActionReaderImpl())
    val player2 = ChoicePlayer("choice_player2", 0, ActionReaderImpl())

    return Pair(player1, player2)
}

private fun createCoolPlayerAndCheatPlayer(): Pair<CoolPlayer, CheatPlayer> {
    val player1 = CoolPlayer("cool_player", 0)
    val player2 = CheatPlayer("cheat_player", 0)

    return Pair(player1, player2)
}

private fun createCopyPlayerAndCheatPlayer(): Pair<CopyPlayer, CheatPlayer> {
    val player1 = CopyPlayer("copy_player", 0)
    val player2 = CheatPlayer("cheat_player", 0)

    return Pair(player1, player2)
}

private fun createCopyPlayerAndGrudgePlayer(): Pair<CopyPlayer, GrudgePlayer> {
    val player1 = CopyPlayer("copy_player", 0)
    val player2 = GrudgePlayer("grudge_player", 0)

    return Pair(player1, player2)
}

private fun createCopyPlayerAndDetectivePlayer(): Pair<CopyPlayer, DetectivePlayer> {
    val player1 = CopyPlayer("copy_player", 0)
    val player2 = DetectivePlayer("detective_player", 0)

    return Pair(player1, player2)
}