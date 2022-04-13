package com.tw

import com.tw.core.game.ConsolePrinter
import com.tw.core.game.GameEngine
import com.tw.core.game.Machine
import com.tw.core.player.Action
import com.tw.core.player.ChoicePlayer
import com.tw.core.player.Player
import com.tw.io.ActionReaderImpl
import com.tw.io.ConsolePrinterImpl
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val INITIAL_SCORE = 0

internal class MachineTest {

    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var gameEngine: GameEngine
    private lateinit var consolePrinter: ConsolePrinter

    @BeforeEach
    internal fun setUp() {
        player1 = spyk(ChoicePlayer("player1", INITIAL_SCORE, ActionReaderImpl()))
        player2 = spyk(ChoicePlayer("player2", INITIAL_SCORE, ActionReaderImpl()))
        gameEngine = GameEngine()
        consolePrinter = spyk(ConsolePrinterImpl())
    }

    @Test
    internal fun `0 round`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinter)

        every { player1.doAction() } returns Action.CHEAT
        every { player2.doAction() } returns Action.CHEAT

        machine.playGame(0)

        verify(exactly = 0) {
            player1.updateScore(any())
            player2.updateScore(any())
            consolePrinter.displayScore(player1, player2)
        }
        verify(exactly = 1) {
            consolePrinter.displayWinner(null)
        }
    }

    @Test
    internal fun `chat vs cheat should draw with 0 points`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinter)

        every { player1.doAction() } returns Action.CHEAT
        every { player2.doAction() } returns Action.CHEAT

        machine.playGame(1)

        verify(exactly = 1) {
            player1.updateScore(INITIAL_SCORE)
            player2.updateScore(INITIAL_SCORE)
            consolePrinter.displayScore(player1, player2)
            consolePrinter.displayWinner(null)
        }
    }

    @Test
    internal fun `cheat vs cooperate should win cheat with 3 points`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinter)

        every { player1.doAction() } returns Action.CHEAT
        every { player2.doAction() } returns Action.COOPERATE

        machine.playGame(1)

        verify(exactly = 1) {
            player1.updateScore(3)
            player2.updateScore(-1)
            consolePrinter.displayScore(player1, player2)
            consolePrinter.displayWinner(player1)
        }
    }

    @Test
    internal fun `cooperate vs cheat should loose cooperate with -1 points`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinter)

        every { player1.doAction() } returns Action.COOPERATE
        every { player2.doAction() } returns Action.CHEAT

        machine.playGame(1)

        verify(exactly = 1) {
            player1.updateScore(-1)
            player2.updateScore(3)
            consolePrinter.displayScore(player1, player2)
            consolePrinter.displayWinner(player2)
        }
    }

    @Test
    internal fun `cooperate vs cooperate should draw with 2 points`() {
        val machine = Machine(gameEngine, player1, player2, consolePrinter)

        every { player1.doAction() } returns Action.COOPERATE
        every { player2.doAction() } returns Action.COOPERATE

        machine.playGame(1)

        verify(exactly = 1) {
            player1.updateScore(2)
            player2.updateScore(2)
            consolePrinter.displayScore(player1, player2)
            consolePrinter.displayWinner(null)
        }
    }

    @Test
    internal fun `multi round`() {
        val numRounds = 2
        val machine = Machine(gameEngine, player1, player2, consolePrinter)

        every { player1.doAction() } returnsMany listOf(
            Action.CHEAT,
            Action.CHEAT,
        )
        every { player2.doAction() } returnsMany listOf(
            Action.CHEAT,
            Action.COOPERATE,
        )

        machine.playGame(numRounds)

        verify(exactly = numRounds) {
            player2.updateScore(any())
            consolePrinter.displayScore(player1, player2)
        }
        verify(exactly = 1) { consolePrinter.displayWinner(player1) }
    }
}