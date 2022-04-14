package com.tw.io

import com.tw.core.player.Action
import com.tw.core.player.ActionReader
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class ActionReaderTest {

    private lateinit var reader: Scanner
    private lateinit var actionReader: ActionReader

    @BeforeEach
    internal fun setUp() {
        reader = mockk<Scanner>()
        actionReader = ActionReaderImpl(reader)
    }

    @Test
    fun `should return CHEAT action when user press 0`() {
        every { reader.nextInt() } returns 0

        assertThat(actionReader.readAction()).isEqualTo(Action.CHEAT)
    }

    @Test
    fun `should return COOPERATE action when user press 1`() {
        every { reader.nextInt() } returns 1

        assertThat(actionReader.readAction()).isEqualTo(Action.COOPERATE)
    }
}