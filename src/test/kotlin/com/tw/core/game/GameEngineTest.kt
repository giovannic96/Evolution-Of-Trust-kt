package com.tw.core.game

import com.tw.core.action.Action
import com.tw.core.action.ActionReader
import com.tw.core.player.ChoicePlayer
import com.tw.utils.PlayerConstants.CHOICE_PLAYER_NAME
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
        actionReader = mockk()
    }

    @Test
    internal fun `player one wins`() {
        val player1 = ChoicePlayer(CHOICE_PLAYER_NAME+"1", 2, actionReader)
        val player2 = ChoicePlayer(CHOICE_PLAYER_NAME+"2", 1, actionReader)

        assertThat(gameEngine.calculateWinner(player1, player2)).isEqualTo(player1)
    }

    @Test
    internal fun draw() {
        val player1 = ChoicePlayer(CHOICE_PLAYER_NAME+"1", 1, actionReader)
        val player2 = ChoicePlayer(CHOICE_PLAYER_NAME+"2", 1, actionReader)

        assertThat(gameEngine.calculateWinner(player1, player2)).isNull()
    }

    @Test
    internal fun `player two wins`() {
        val player1 = ChoicePlayer(CHOICE_PLAYER_NAME+"1", 1, actionReader)
        val player2 = ChoicePlayer(CHOICE_PLAYER_NAME+"2", 2, actionReader)

        assertThat(gameEngine.calculateWinner(player1, player2)).isEqualTo(player2)
    }

    @Test
    internal fun `cheat vs cheat`() {
        val actualPoints = gameEngine.calculatePointsForActions(Action.CHEAT, Action.CHEAT)

        assertThat(actualPoints.player1Points).isEqualTo(0)
        assertThat(actualPoints.player2Points).isEqualTo(0)
    }

    @Test
    internal fun `cheat vs cooperate`() {
        val actualPoints = gameEngine.calculatePointsForActions(Action.CHEAT, Action.COOPERATE)

        assertThat(actualPoints.player1Points).isEqualTo(3)
        assertThat(actualPoints.player2Points).isEqualTo(-1)
    }

    @Test
    internal fun `cooperate vs cheat`() {
        val actualPoints = gameEngine.calculatePointsForActions(Action.COOPERATE, Action.CHEAT)

        assertThat(actualPoints.player1Points).isEqualTo(-1)
        assertThat(actualPoints.player2Points).isEqualTo(3)
    }

    @Test
    internal fun `cooperate vs cooperate`() {
        val actualPoints = gameEngine.calculatePointsForActions(Action.COOPERATE, Action.COOPERATE)

        assertThat(actualPoints.player1Points).isEqualTo(2)
        assertThat(actualPoints.player2Points).isEqualTo(2)
    }
}
