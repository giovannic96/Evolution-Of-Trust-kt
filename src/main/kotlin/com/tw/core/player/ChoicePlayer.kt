package com.tw.core.player

import com.tw.core.action.Action
import com.tw.core.action.ActionReader

class ChoicePlayer(
    name: String,
    score: Int,
    private val actionReader: ActionReader
) : AbstractPlayer(name, score) {

    override fun doAction(): Action {
        return actionReader.readAction()
    }
}
