package com.tw.core.game

import com.tw.core.player.*
import com.tw.doubles.StatsPrinterSpy
import com.tw.utils.PlayerConstants.CHEAT_PLAYER_NAME
import com.tw.utils.PlayerConstants.COOL_PLAYER_NAME
import com.tw.utils.PlayerConstants.COPY_PLAYER_NAME
import com.tw.utils.PlayerConstants.GRUDGE_PLAYER_NAME
import com.tw.utils.PlayerConstants.INITIAL_SCORE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MatchTest {

    private lateinit var gameEngine: GameEngine
    private lateinit var lastActionWrapper: LastActionWrapper
    private lateinit var consolePrinterSpy: StatsPrinterSpy
    private lateinit var cheatPlayer: Player
    private lateinit var coolPlayer: Player
    private lateinit var copyPlayer: Player
    private lateinit var grudgePlayer: Player

    @BeforeEach
    internal fun setUp() {
        gameEngine = GameEngine()
        lastActionWrapper = LastActionWrapper()
        consolePrinterSpy = StatsPrinterSpy()
        cheatPlayer = CheatPlayer(CHEAT_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)
        coolPlayer = CoolPlayer(COOL_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)
        copyPlayer = CopyPlayer(COPY_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)
        grudgePlayer = GrudgePlayer(GRUDGE_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)
    }

    @Test
    internal fun `cheat player vs cheat player`() {
        val machine = Machine(gameEngine, cheatPlayer, cheatPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `cheat player vs cool player`() {
        val machine = Machine(gameEngine, cheatPlayer, coolPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isEqualTo(cheatPlayer)
    }

    @Test
    internal fun `cheat player vs copy player`() {
        val machine = Machine(gameEngine, cheatPlayer, copyPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isEqualTo(cheatPlayer)
    }

    @Test
    internal fun `cheat player vs grudge player`() {
        val machine = Machine(gameEngine, cheatPlayer, grudgePlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isEqualTo(cheatPlayer)
    }

    @Test
    internal fun `cool player vs cool player`() {
        val machine = Machine(gameEngine, coolPlayer, coolPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `cool player vs copy player`() {
        val machine = Machine(gameEngine, coolPlayer, copyPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `cool player vs grudge player`() {
        val machine = Machine(gameEngine, coolPlayer, grudgePlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `copy player vs copy player`() {
        val machine = Machine(gameEngine, copyPlayer, copyPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `copy player vs cheat player`() {
        val machine = Machine(gameEngine, copyPlayer, cheatPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isEqualTo(cheatPlayer)
    }

    @Test
    internal fun `copy player vs grudge player`() {
        val machine = Machine(gameEngine, copyPlayer, grudgePlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `grudge player vs grudge player`() {
        val machine = Machine(gameEngine, grudgePlayer, grudgePlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `grudge player vs copy player`() {
        val machine = Machine(gameEngine, grudgePlayer, copyPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isNull()
    }
}