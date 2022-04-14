package com.tw.core.game

import com.tw.core.player.CheatPlayer
import com.tw.core.player.CoolPlayer
import com.tw.core.player.CopyPlayer
import com.tw.core.player.Player
import com.tw.doubles.ConsolePrinterSpy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val INITIAL_POINTS = 0

internal class MatchTest {

    private lateinit var gameEngine: GameEngine
    private lateinit var consolePrinterSpy: ConsolePrinterSpy
    private lateinit var cheatPlayer: Player
    private lateinit var coolPlayer: Player
    private lateinit var copyPlayer: Player

    @BeforeEach
    internal fun setUp() {
        gameEngine = GameEngine()
        consolePrinterSpy = ConsolePrinterSpy()
        cheatPlayer = CheatPlayer("cheat_player", INITIAL_POINTS)
        coolPlayer = CoolPlayer("cool_player", INITIAL_POINTS)
        copyPlayer = CopyPlayer("copy_player", INITIAL_POINTS)
    }

    @Test
    internal fun `cool player vs cheat player`() {
        val machine = Machine(gameEngine, coolPlayer, cheatPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isEqualTo(cheatPlayer)
    }

    @Test
    internal fun `copy player vs cheat player`() {
        val machine = Machine(gameEngine, copyPlayer, cheatPlayer, consolePrinterSpy)

        machine.playGame(1)

        assertThat(consolePrinterSpy.displayScoreCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.displayWinnerCounter).isEqualTo(1)
        assertThat(consolePrinterSpy.winnerPlayer).isEqualTo(cheatPlayer)
    }
}