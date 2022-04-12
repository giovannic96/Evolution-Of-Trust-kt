package com.tw

import com.tw.game.game.ConsolePrinter
import com.tw.game.game.GameEngine
import com.tw.game.player.*
import com.tw.peripheral.ActionReaderImpl
import com.tw.peripheral.ConsolePrinterImpl
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val INITIAL_SCORE = 0

internal class MachineTest {

    private lateinit var consolePlayer1: Player
    private lateinit var consolePlayer2: Player
    private lateinit var coolPlayer: Player
    private lateinit var cheatPlayer: Player
    private lateinit var gameEngine: GameEngine
    private lateinit var consolePrinter: ConsolePrinter

    @BeforeEach
    internal fun setUp() {
        consolePlayer1 = spyk(ConsolePlayer("console_player1", INITIAL_SCORE, ActionReaderImpl()))
        consolePlayer2 = spyk(ConsolePlayer("console_player2", INITIAL_SCORE, ActionReaderImpl()))
        coolPlayer = CoolPlayer("cool_player", INITIAL_SCORE)
        cheatPlayer = CheatPlayer("cheat_player", INITIAL_SCORE)
        gameEngine = GameEngine()
        consolePrinter = spyk(ConsolePrinterImpl())
    }

    @Test
    internal fun `console player CHEAT vs console player CHEAT`() {
        val machine = Machine(gameEngine, consolePlayer1, consolePlayer2, consolePrinter)

        every { consolePlayer1.doAction() } returns Action.CHEAT
        every { consolePlayer2.doAction() } returns Action.CHEAT

        machine.playGame(1)

        verify {
            consolePlayer1.updateScore(INITIAL_SCORE)
            consolePlayer2.updateScore(INITIAL_SCORE)
        }
    }

    @Test
    internal fun `console player CHEAT vs console player COOPERATE`() {
        val machine = Machine(gameEngine, consolePlayer1, consolePlayer2, consolePrinter)

        every { consolePlayer1.doAction() } returns Action.CHEAT
        every { consolePlayer2.doAction() } returns Action.COOPERATE

        machine.playGame(1)

        verify {
            consolePlayer1.updateScore(3)
            consolePlayer2.updateScore(-1)
        }
    }

    @Test
    internal fun `console player COOPERATE vs console player CHEAT`() {
        val machine = Machine(gameEngine, consolePlayer1, consolePlayer2, consolePrinter)

        every { consolePlayer1.doAction() } returns Action.COOPERATE
        every { consolePlayer2.doAction() } returns Action.CHEAT

        machine.playGame(1)

        verify {
            consolePlayer1.updateScore(-1)
            consolePlayer2.updateScore(3)
        }
    }

    @Test
    internal fun `console player COOPERATE vs console player COOPERATE`() {
        val machine = Machine(gameEngine, consolePlayer1, consolePlayer2, consolePrinter)

        every { consolePlayer1.doAction() } returns Action.COOPERATE
        every { consolePlayer2.doAction() } returns Action.COOPERATE

        machine.playGame(1)

        verify {
            consolePlayer1.updateScore(2)
            consolePlayer2.updateScore(2)
        }
    }

    @Test
    internal fun `console player1 vs console player2 - no winner`() {
        val numRounds = 4
        val machine = Machine(gameEngine, consolePlayer1, consolePlayer2, consolePrinter)

        every { consolePlayer1.doAction() } returnsMany listOf(
            Action.CHEAT,
            Action.CHEAT,
            Action.COOPERATE,
            Action.COOPERATE
        )
        every { consolePlayer2.doAction() } returnsMany listOf(
            Action.CHEAT,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE
        )

        machine.playGame(numRounds)

        verify(exactly = numRounds) { consolePrinter.displayScore(consolePlayer1, consolePlayer2) }
        verify(exactly = 1) { consolePrinter.displayWinner(null) }
    }

    @Test
    internal fun `console player1 vs console player2 - player1 winner`() {
        val numRounds = 4
        val machine = Machine(gameEngine, consolePlayer1, consolePlayer2, consolePrinter)

        every { consolePlayer1.doAction() } returnsMany listOf(
            Action.CHEAT,
            Action.CHEAT,
            Action.CHEAT,
            Action.COOPERATE
        )
        every { consolePlayer2.doAction() } returnsMany listOf(
            Action.CHEAT,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE
        )

        machine.playGame(numRounds)

        verify(exactly = numRounds) { consolePrinter.displayScore(consolePlayer1, consolePlayer2) }
        verify(exactly = 1) { consolePrinter.displayWinner(consolePlayer1) }
    }

    @Test
    internal fun `console player1 vs console player2 - player2 winner`() {
        val numRounds = 4
        val machine = Machine(gameEngine, consolePlayer1, consolePlayer2, consolePrinter)

        every { consolePlayer1.doAction() } returnsMany listOf(
            Action.CHEAT,
            Action.COOPERATE,
            Action.COOPERATE,
            Action.COOPERATE
        )
        every { consolePlayer2.doAction() } returnsMany listOf(
            Action.CHEAT,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE
        )

        machine.playGame(numRounds)

        verify(exactly = numRounds) { consolePrinter.displayScore(consolePlayer1, consolePlayer2) }
        verify(exactly = 1) { consolePrinter.displayWinner(consolePlayer2) }
    }

    @Test
    internal fun `cool player vs cheat player`() {
        val numRounds = 3
        val machine = Machine(gameEngine, coolPlayer, cheatPlayer, consolePrinter)

        machine.playGame(numRounds)

        assertThat(gameEngine.calculateWinner(coolPlayer, cheatPlayer)).isEqualTo(cheatPlayer)
        verify(exactly = numRounds) { consolePrinter.displayScore(coolPlayer, cheatPlayer) }
        verify(exactly = 1) { consolePrinter.displayWinner(cheatPlayer) }
    }
}