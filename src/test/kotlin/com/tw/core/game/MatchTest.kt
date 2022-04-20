package com.tw.core.game

import com.tw.core.player.*
import com.tw.doubles.StatsPrinterSpy
import com.tw.utils.PlayerConstants.CHEAT_PLAYER_NAME
import com.tw.utils.PlayerConstants.COOL_PLAYER_NAME
import com.tw.utils.PlayerConstants.COPY_PLAYER_NAME
import com.tw.utils.PlayerConstants.DETECTIVE_PLAYER_NAME
import com.tw.utils.PlayerConstants.GRUDGE_PLAYER_NAME
import com.tw.utils.PlayerConstants.INITIAL_SCORE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val NUM_ROUNDS = 5

internal class MatchTest {

    private lateinit var gameEngine: GameEngine
    private lateinit var statsPrinterSpy: StatsPrinterSpy
    private lateinit var cheatPlayer: Player
    private lateinit var coolPlayer: Player
    private lateinit var copyPlayer: Player
    private lateinit var grudgePlayer: Player
    private lateinit var detectivePlayer: Player

    @BeforeEach
    internal fun setUp() {
        gameEngine = GameEngine()
        statsPrinterSpy = StatsPrinterSpy()
        cheatPlayer = CheatPlayer(CHEAT_PLAYER_NAME, INITIAL_SCORE)
        coolPlayer = CoolPlayer(COOL_PLAYER_NAME, INITIAL_SCORE)
        copyPlayer = CopyPlayer(COPY_PLAYER_NAME, INITIAL_SCORE)
        grudgePlayer = GrudgePlayer(GRUDGE_PLAYER_NAME, INITIAL_SCORE)
        detectivePlayer = DetectivePlayer(DETECTIVE_PLAYER_NAME, INITIAL_SCORE)
    }

    @Test
    internal fun `cool player vs cheat player`() {
        val machine = Machine(gameEngine, coolPlayer, cheatPlayer, statsPrinterSpy)

        machine.playGame(NUM_ROUNDS)

        assertThat(statsPrinterSpy.getRoundScores()).isEqualTo(listOf(
            Pair(-1,3),
            Pair(-2,6),
            Pair(-3,9),
            Pair(-4,12),
            Pair(-5,15),
        ))
        assertThat(statsPrinterSpy.winnerPlayer).isEqualTo(cheatPlayer)
    }

    @Test
    internal fun `copy player vs cheat player`() {
        val machine = Machine(gameEngine, copyPlayer, cheatPlayer, statsPrinterSpy)

        machine.playGame(NUM_ROUNDS)

        assertThat(statsPrinterSpy.getRoundScores()).isEqualTo(listOf(
            Pair(-1,3),
            Pair(-1,3),
            Pair(-1,3),
            Pair(-1,3),
            Pair(-1,3),
        ))
        assertThat(statsPrinterSpy.winnerPlayer).isEqualTo(cheatPlayer)
    }

    @Test
    internal fun `copy player vs grudge player`() {
        val machine = Machine(gameEngine, copyPlayer, grudgePlayer, statsPrinterSpy)

        machine.playGame(NUM_ROUNDS)

        assertThat(statsPrinterSpy.getRoundScores()).isEqualTo(listOf(
            Pair(2,2),
            Pair(4,4),
            Pair(6,6),
            Pair(8,8),
            Pair(10,10),
        ))
        assertThat(statsPrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `copy player vs detective player`() {
        val machine = Machine(gameEngine, copyPlayer, detectivePlayer, statsPrinterSpy)

        machine.playGame(NUM_ROUNDS)

        assertThat(statsPrinterSpy.getRoundScores()).isEqualTo(listOf(
            Pair(2,2),
            Pair(1,5),
            Pair(4,4),
            Pair(6,6),
            Pair(8,8),
        ))
        assertThat(statsPrinterSpy.winnerPlayer).isNull()
    }

    @Test
    internal fun `detective player vs copy player`() {
        val machine = Machine(gameEngine, detectivePlayer, copyPlayer, statsPrinterSpy)

        machine.playGame(NUM_ROUNDS)

        assertThat(statsPrinterSpy.getRoundScores()).isEqualTo(listOf(
            Pair(2,2),
            Pair(5,1),
            Pair(4,4),
            Pair(6,6),
            Pair(8,8),
        ))
        assertThat(statsPrinterSpy.winnerPlayer).isNull()
    }
}