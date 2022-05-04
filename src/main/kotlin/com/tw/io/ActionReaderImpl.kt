package com.tw.io

import com.tw.core.action.Action
import com.tw.core.action.ActionReader
import java.util.*


class ActionReaderImpl(private val reader: Scanner = Scanner(System.`in`)) : ActionReader {

    override fun readAction(): Action {
        print("Which action do you want to perform? (0 to CHEAT, 1 to COOPERATE)")
        val input = reader.nextInt()

        return getActionByInput(input)
    }

    private fun getActionByInput(input: Int): Action {
        return when (input) {
            0 -> Action.CHEAT
            1 -> Action.COOPERATE
            else -> throw IllegalStateException("Please insert a valid number (0 to CHEAT, 1 to COOPERATE)")
        }
    }
}