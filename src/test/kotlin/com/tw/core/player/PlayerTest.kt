package com.tw.core.player

import com.tw.utils.PlayerConstants.CHEAT_PLAYER_NAME
import com.tw.utils.PlayerConstants.CHOICE_PLAYER_NAME
import com.tw.utils.PlayerConstants.COOL_PLAYER_NAME
import com.tw.utils.PlayerConstants.COPY_PLAYER_NAME
import com.tw.utils.PlayerConstants.DETECTIVE_PLAYER_NAME
import com.tw.utils.PlayerConstants.GRUDGE_PLAYER_NAME
import com.tw.utils.PlayerConstants.INITIAL_SCORE
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PlayerTest {

    private lateinit var actionReader: ActionReader
    private lateinit var lastActionWrapper: LastActionWrapper

    @BeforeEach
    internal fun setUp() {
        lastActionWrapper = LastActionWrapper()
        actionReader = mockk()
    }

    @Test
    internal fun `update coin amount`() {
        val player = ChoicePlayer(CHOICE_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper, actionReader)

        player.updateScore(3)
        val expectedAmount = 3

        assertThat(player.getScore()).isEqualTo(expectedAmount)
    }

    @Test
    internal fun `player one cheats`() {
        val player = ChoicePlayer(CHOICE_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper, actionReader)

        every { actionReader.readAction() } returns Action.CHEAT

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `player one cooperates`() {
        val player = ChoicePlayer(CHOICE_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper, actionReader)

        every { actionReader.readAction() } returns Action.COOPERATE

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cool player always cooperates`() {
        val player = CoolPlayer(COOL_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cheat player always cheats`() {
        val player = CheatPlayer(CHEAT_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `copy player cooperates then keeps copying`() {
        val lastActionWrapper = mockk<LastActionWrapper>()
        val copyPlayer = CopyPlayer(COPY_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)

        every { lastActionWrapper.setLastAction(any()) } returns Unit
        every { lastActionWrapper.getLastAction() } returnsMany listOf(
            null,
            Action.CHEAT,
            Action.COOPERATE
        )

        assertThat(copyPlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(copyPlayer.doAction()).isEqualTo(Action.CHEAT)
        assertThat(copyPlayer.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `grudge player cooperates then keeps cheating if the other cheated`() {
        val lastActionWrapper = mockk<LastActionWrapper>()
        val grudgePlayer = GrudgePlayer(GRUDGE_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)

        every { lastActionWrapper.setLastAction(any()) } returns Unit
        every { lastActionWrapper.getLastAction() } returnsMany listOf(
            Action.COOPERATE,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE,
        )

        assertThat(grudgePlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(grudgePlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(grudgePlayer.doAction()).isEqualTo(Action.CHEAT)
        assertThat(grudgePlayer.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `detective player analyzes then acts like copycat if the other cheated`() {
        val lastActionWrapper = mockk<LastActionWrapper>()
        val detectivePlayer = DetectivePlayer(DETECTIVE_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)

        every { lastActionWrapper.setLastAction(any()) } returns Unit
        every { lastActionWrapper.getLastAction() } returnsMany listOf(
            Action.COOPERATE,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE
        )

        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `detective player analyzes then acts like cheat player if the other never cheated`() {
        val lastActionWrapper = mockk<LastActionWrapper>()
        val detectivePlayer = DetectivePlayer(DETECTIVE_PLAYER_NAME, INITIAL_SCORE, lastActionWrapper)

        every { lastActionWrapper.setLastAction(any()) } returns Unit
        every { lastActionWrapper.getLastAction() } returnsMany listOf(
            Action.COOPERATE,
            Action.COOPERATE,
            Action.COOPERATE,
            Action.COOPERATE,
            Action.COOPERATE,
            Action.CHEAT,
            Action.COOPERATE
        )

        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.COOPERATE)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)
        assertThat(detectivePlayer.doAction()).isEqualTo(Action.CHEAT)
    }
}