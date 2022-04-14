package com.tw.io

import com.tw.core.game.StatsPrinter
import com.tw.core.player.LastActionWrapper
import com.tw.core.player.CoolPlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class StatsPrinterTest {

    private lateinit var outputStreamCaptor: ByteArrayOutputStream
    private lateinit var standardOut: PrintStream
    private lateinit var statsPrinter: StatsPrinter
    private lateinit var lastActionWrapper: LastActionWrapper

    @BeforeEach
    fun setUp() {
        outputStreamCaptor = ByteArrayOutputStream()
        standardOut = System.out
        statsPrinter = StatsPrinterImpl()
        lastActionWrapper = LastActionWrapper()
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    internal fun `should display score on standard output`() {
        val player1 = CoolPlayer("p1", 0, lastActionWrapper)
        val player2 = CoolPlayer("p2", 0, lastActionWrapper)

        statsPrinter.displayScore(player1, player2)
        val expectedString =
            "[${player1.getName()}] Score: ${player1.getScore()}\n[${player2.getName()}] Score: ${player2.getScore()}"

        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(expectedString)
    }

    @Test
    internal fun `should display winner sentence when player is not null`() {
        val player = CoolPlayer("p1", 0, lastActionWrapper)

        statsPrinter.displayWinner(player)
        val expectedString = "The winner is ${player.getName()}!"

        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(expectedString)
    }

    @Test
    internal fun `should display draw sentence when player is null`() {
        statsPrinter.displayWinner(null)
        val expectedString = "The game ended in a draw!"

        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(expectedString)
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }
}