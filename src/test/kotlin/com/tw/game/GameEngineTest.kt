package com.tw.game

import com.tw.game.game.GameEngine
import com.tw.game.game.Points
import com.tw.game.player.Action
import com.tw.game.player.ActionReader
import com.tw.game.player.ConsolePlayer
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class GameEngineTest {

    private lateinit var gameEngine: GameEngine
    private lateinit var actionReader: ActionReader

    @BeforeEach
    internal fun setUp() {
        gameEngine = GameEngine()
        actionReader = mockk<ActionReader>()
    }

    @Test
    internal fun `player one wins`() {
        val player1 = ConsolePlayer("p1", 2, actionReader)
        val player2 = ConsolePlayer("p2", 1, actionReader)

        assertThat(gameEngine.calculateWinner(player1, player2)).isEqualTo(player1)
    }

    @Test
    internal fun draw() {
        val player1 = ConsolePlayer("p1", 1, actionReader)
        val player2 = ConsolePlayer("p2", 1, actionReader)

        assertThat(gameEngine.calculateWinner(player1, player2)).isNull()
    }

    @Test
    internal fun `player two wins`() {
        val player1 = ConsolePlayer("p1", 1, actionReader)
        val player2 = ConsolePlayer("p2", 2, actionReader)

        assertThat(gameEngine.calculateWinner(player1, player2)).isEqualTo(player2)
    }

    @Test
    internal fun `cheat vs cheat`() {
        val player1Action = Action.CHEAT
        val player2Action = Action.CHEAT

        val expectedPoints = Points(0, 0)

        assertThat(gameEngine.calculatePointsForActions(player1Action, player2Action)).usingRecursiveComparison()
            .isEqualTo(expectedPoints)
    }

    @Test
    internal fun `cheat vs cooperate`() {
        val player1Action = Action.CHEAT
        val player2Action = Action.COOPERATE

        val expectedPoints = Points(3, -1)

        assertThat(gameEngine.calculatePointsForActions(player1Action, player2Action)).usingRecursiveComparison()
            .isEqualTo(expectedPoints)
    }

    @Test
    internal fun `cooperate vs cheat`() {
        val player1Action = Action.COOPERATE
        val player2Action = Action.CHEAT

        val expectedPoints = Points(-1, 3)

        assertThat(gameEngine.calculatePointsForActions(player1Action, player2Action)).usingRecursiveComparison()
            .isEqualTo(expectedPoints)
    }

    @Test
    internal fun `cooperate vs cooperate`() {
        val player1Action = Action.COOPERATE
        val player2Action = Action.COOPERATE

        val expectedPoints = Points(2, 2)

        assertThat(gameEngine.calculatePointsForActions(player1Action, player2Action)).usingRecursiveComparison()
            .isEqualTo(expectedPoints)
    }
}
