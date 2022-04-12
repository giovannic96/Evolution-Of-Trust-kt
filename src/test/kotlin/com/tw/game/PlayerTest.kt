package com.tw.game

import com.tw.game.player.*
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PlayerTest {

    private lateinit var actionReader: ActionReader

    @BeforeEach
    internal fun setUp() {
        actionReader = mockk<ActionReader>()
    }

    @Test
    internal fun `update coin amount`() {
        val player = ConsolePlayer("p1", 0, actionReader)

        player.updateScore(3)
        val expectedAmount = 3

        assertThat(player.getScore()).isEqualTo(expectedAmount)
    }

    @Test
    internal fun `player one cheats`() {
        val player = ConsolePlayer("p1", 0, actionReader)

        every { actionReader.readAction() } returns Action.CHEAT

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    internal fun `player one cooperates`() {
        val player = ConsolePlayer("p1", 0, actionReader)

        every { actionReader.readAction() } returns Action.COOPERATE

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cool player always cooperates`() {
        val player = CoolPlayer("p1", 0)

        assertThat(player.doAction()).isEqualTo(Action.COOPERATE)
    }

    @Test
    internal fun `cheat player always cheats`() {
        val player = CheatPlayer("p1", 0)

        assertThat(player.doAction()).isEqualTo(Action.CHEAT)
    }
}