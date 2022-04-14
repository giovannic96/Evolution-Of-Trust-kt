package com.tw.core.player

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val INITIAL_SCORE = 0

internal class PlayerTest {

    private lateinit var actionReader: ActionReader

    @BeforeEach
    internal fun setUp() {
        actionReader = mockk<ActionReader>()
    }

    @Test
    internal fun `update coin amount`() {
        val player = ChoicePlayer("console_player", INITIAL_SCORE, actionReader = actionReader)

        player.updateScore(3)
        val expectedAmount = 3

        assertThat(player.getScore()).isEqualTo(expectedAmount)
    }

    @Test
    internal fun `player one cheats`() {
        val player = ChoicePlayer("console_player", INITIAL_SCORE, actionReader = actionReader)

        every { actionReader.readAction() } returns Action.CHEAT

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `player one cooperates`() {
        val player = ChoicePlayer("console_player", INITIAL_SCORE, actionReader = actionReader)

        every { actionReader.readAction() } returns Action.COOPERATE

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cool player always cooperates`() {
        val player = CoolPlayer("cool_player", INITIAL_SCORE)

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cheat player always cheats`() {
        val player = CheatPlayer("cheat_player", INITIAL_SCORE)

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }

    /*
    @Test
    internal fun `copy player collaborate then copy`() {
        val cheatPlayer = CheatPlayer("cheat_player", INITIAL_SCORE)
        val copyPlayer = CopyPlayer("copy_player", INITIAL_SCORE)

        cheatPlayer.doAction()
        val copyPlayerFirstAction = copyPlayer.doAction()

        cheatPlayer.doAction()
        val copyPlayerSecondAction = copyPlayer.doAction()

        cheatPlayer.doAction()
        val copyPlayerThirdAction = copyPlayer.doAction()

        assertThat(copyPlayerFirstAction).isEqualTo(Action.COOPERATE)
        assertThat(copyPlayerSecondAction).isEqualTo(Action.CHEAT)
        assertThat(copyPlayerThirdAction).isEqualTo(Action.CHEAT)
    }
     */
}