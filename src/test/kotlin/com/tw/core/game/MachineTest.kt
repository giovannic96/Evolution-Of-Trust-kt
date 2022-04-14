package com.tw.core.game

import com.tw.core.player.Action
import com.tw.doubles.ConsolePrinterSpy
import com.tw.doubles.PlayerSpy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val INITIAL_SCORE = 0

internal class MachineTest {

    private lateinit var player1: PlayerSpy
    private lateinit var player2: PlayerSpy
    private lateinit var gameEngine: GameEngine
    private lateinit var consolePrinterSpy: ConsolePrinterSpy

    @BeforeEach
    internal fun setUp() {
        consolePrinterSpy = ConsolePrinterSpy()
        player1 = PlayerSpy()
        player2 = PlayerSpy()
        gameEngine = GameEngine()
    }

    @Test
    internal fun `0 round`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinterSpy)

        machine.playGame(0)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(0)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
        assertThat(player1.scores).isEqualTo(emptyList<Int>())
        assertThat(player2.scores).isEqualTo(emptyList<Int>())
    }

    @Test
    internal fun `chat vs cheat should draw with 0 points`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinterSpy)

        player2.actions.add(Action.CHEAT)
        player1.actions.add(Action.CHEAT)

        machine.playGame(1)

        assertThat(player1.scores).isEqualTo(listOf(INITIAL_SCORE))
        assertThat(player2.scores).isEqualTo(listOf(INITIAL_SCORE))
        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `cheat vs cooperate should win cheat with 3 points`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinterSpy)

        player1.actions.add(Action.CHEAT)
        player2.actions.add(Action.COOPERATE)

        machine.playGame(1)

        assertThat(player1.scores).isEqualTo(listOf(3))
        assertThat(player2.scores).isEqualTo(listOf(-1))
        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isEqualTo(player1)
    }

    @Test
    internal fun `cooperate vs cheat should loose cooperate with -1 points`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinterSpy)

        player1.actions.add(Action.COOPERATE)
        player2.actions.add(Action.CHEAT)

        machine.playGame(1)

        assertThat(player1.scores).isEqualTo(listOf(-1))
        assertThat(player2.scores).isEqualTo(listOf(3))
        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isEqualTo(player2)
    }

    @Test
    internal fun `cooperate vs cooperate should draw with 2 points`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinterSpy)

        player1.actions.add(Action.COOPERATE)
        player2.actions.add(Action.COOPERATE)

        machine.playGame(1)

        assertThat(player1.scores).isEqualTo(listOf(2))
        assertThat(player2.scores).isEqualTo(listOf(2))
        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `multi round`() {
        val numRounds = 2
        val machine = Machine(gameEngine, player1, player2, consolePrinterSpy)

        player1.actions.addAll(listOf(Action.CHEAT, Action.CHEAT))
        player2.actions.addAll(listOf(Action.CHEAT, Action.COOPERATE))

        machine.playGame(numRounds)

        assertThat(player1.scores).isEqualTo(listOf(0,3))
        assertThat(player2.scores).isEqualTo(listOf(0,-1))
        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(numRounds)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isEqualTo(player1)
    }
}

