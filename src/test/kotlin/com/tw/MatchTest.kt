package com.tw

import com.tw.core.game.ConsolePrinter
import com.tw.core.game.GameEngine
import com.tw.core.game.Machine
import com.tw.core.player.CheatPlayer
import com.tw.core.player.CoolPlayer
import com.tw.core.player.Player
import com.tw.io.ConsolePrinterImpl
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val INITIAL_POINTS = 0

internal class MatchTest {

    private lateinit var gameEngine: GameEngine
    private lateinit var consolePrinter: ConsolePrinter
    private lateinit var cheatPlayer: Player
    private lateinit var coolPlayer: Player

    @BeforeEach
    internal fun setUp() {
        gameEngine = GameEngine()
        consolePrinter = spyk(ConsolePrinterImpl())
        cheatPlayer = CheatPlayer("cheat_player", INITIAL_POINTS)
        coolPlayer = CoolPlayer("cool_player", INITIAL_POINTS)
    }

    @Test
    internal fun `cool player vs cheat player`() {
        val machine = Machine(gameEngine, coolPlayer, cheatPlayer, consolePrinter)

        machine.playGame(1)

        verify(exactly = 1) {
            consolePrinter.displayScore(coolPlayer, cheatPlayer)
            consolePrinter.displayWinner(cheatPlayer)
        }
    }
}