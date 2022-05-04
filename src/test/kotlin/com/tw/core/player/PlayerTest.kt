package com.tw.core.player

import com.tw.core.action.Action
import com.tw.core.action.ActionReader
import com.tw.utils.PlayerConstants.CHEAT_PLAYER_NAME
import com.tw.utils.PlayerConstants.CHOICE_PLAYER_NAME
import com.tw.utils.PlayerConstants.COOL_PLAYER_NAME
import com.tw.utils.PlayerConstants.COPY_PLAYER_NAME
import com.tw.utils.PlayerConstants.DETECTIVE_PLAYER_NAME
import com.tw.utils.PlayerConstants.GRUDGE_PLAYER_NAME
import com.tw.utils.PlayerConstants.INITIAL_SCORE
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PlayerTest {

    private lateinit var actionReader: ActionReader

    @BeforeEach
    internal fun setUp() {
        actionReader = mockk()
    }

    @Test
    internal fun `update coin amount`() {
        val player = ChoicePlayer(CHOICE_PLAYER_NAME, INITIAL_SCORE, actionReader)

        player.updateScore(3)
        val expectedAmount = 3

        assertThat(player.getScore()).isEqualTo(expectedAmount)
    }

    @Test
    internal fun `player one cheats`() {
        val player = ChoicePlayer(CHOICE_PLAYER_NAME, INITIAL_SCORE, actionReader)

        every { actionReader.readAction() } returns Action.CHEAT

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `player one cooperates`() {
        val player = ChoicePlayer(CHOICE_PLAYER_NAME, INITIAL_SCORE, actionReader)

        every { actionReader.readAction() } returns Action.COOPERATE

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cool player always cooperates`() {
        val player = CoolPlayer(COOL_PLAYER_NAME, INITIAL_SCORE)

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cheat player always cheats`() {
        val player = CheatPlayer(CHEAT_PLAYER_NAME, INITIAL_SCORE)

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `copy player cooperates then keeps copying`() {
        val copyPlayer = spyk(CopyPlayer(COPY_PLAYER_NAME, INITIAL_SCORE))

        every { copyPlayer.determineOpponentLastActionByRoundScore(any()) } returnsMany listOf(
            null,
            Action.CHEAT,
            Action.COOPERATE
        )

        copyPlayer.updateScore(0)
        assertThat(copyPlayer.doAction()).isEqualTo(Action.COOPERATE)

        copyPlayer.updateScore(0)
        assertThat(copyPlayer.doAction()).isEqualTo(Action.CHEAT)

        copyPlayer.updateScore(0)
        assertThat(copyPlayer.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `grudge player cooperates then keeps cheating if the opponent cheated`() {
        val grudgePlayer = spyk(GrudgePlayer(GRUDGE_PLAYER_NAME, INITIAL_SCORE))

        every { grudgePlayer.determineOpponentLastActionByRoundScore(any()) } returnsMany listOf(
            Action.COOPERATE,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE,
        )

        grudgePlayer.updateScore(0)
        assertThat(grudgePlayer.doAction()).isEqualTo(Action.COOPERATE)

        grudgePlayer.updateScore(0)
        assertThat(grudgePlayer.doAction()).isEqualTo(Action.COOPERATE)

        grudgePlayer.updateScore(0)
        assertThat(grudgePlayer.doAction()).isEqualTo(Action.CHEAT)

        grudgePlayer.updateScore(0)
        assertThat(grudgePlayer.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `detective player analyzes then acts like copy player if the opponent cheated`() {
        val detectivePlayer = spyk(DetectivePlayer(DETECTIVE_PLAYER_NAME, INITIAL_SCORE))

        every { detectivePlayer.determineOpponentLastActionByRoundScore(any()) } returnsMany listOf(
            Action.COOPERATE,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE
        )

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `detective player analyzes then acts like cheat player if the opponent never cheated`() {
        val detectivePlayer = spyk(DetectivePlayer(DETECTIVE_PLAYER_NAME, INITIAL_SCORE))

        every { detectivePlayer.determineOpponentLastActionByRoundScore(any()) } returnsMany listOf(
            Action.COOPERATE,
            Action.COOPERATE,
            Action.COOPERATE,
            Action.COOPERATE,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE
        )

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)

        detectivePlayer.updateScore(0)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)
    }
}