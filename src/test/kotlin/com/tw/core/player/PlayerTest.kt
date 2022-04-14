package com.tw.core.player

import com.tw.utils.PlayerConstants.INITIAL_SCORE
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PlayerTest {

    private lateinit var actionReader: ActionReader
    private lateinit var actionqQueue: LastActionWrapper

    @BeforeEach
    internal fun setUp() {
        actionqQueue = LastActionWrapper()
        actionReader = mockk()
    }

    @Test
    internal fun `update coin amount`() {
        val player = ChoicePlayer("console_player", INITIAL_SCORE, actionqQueue, actionReader)

        player.updateScore(3)
        val expectedAmount = 3

        assertThat(player.getScore()).isEqualTo(expectedAmount)
    }

    @Test
    internal fun `player one cheats`() {
        val player = ChoicePlayer("console_player", INITIAL_SCORE, actionqQueue, actionReader)

        every { actionReader.readAction() } returns Action.CHEAT

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `player one cooperates`() {
        val player = ChoicePlayer("console_player", INITIAL_SCORE, actionqQueue, actionReader)

        every { actionReader.readAction() } returns Action.COOPERATE

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cool player always cooperates`() {
        val player = CoolPlayer("cool_player", INITIAL_SCORE, actionqQueue)

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cheat player always cheats`() {
        val player = CheatPlayer("cheat_player", INITIAL_SCORE, actionqQueue)

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `copy player collaborate then copy`() {
        val lastActionWrapper = mockk<LastActionWrapper>()
        val copyPlayer = CopyPlayer("copy_player", INITIAL_SCORE, lastActionWrapper)

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
}