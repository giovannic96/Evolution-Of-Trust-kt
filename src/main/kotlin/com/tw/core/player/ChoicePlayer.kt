package com.tw.core.player

class ChoicePlayer(
    name: String,
    score: Int,
    private val actionReader: ActionReader
) : AbstractPlayer(name, score) {

    override fun doAction(): Action {
        return actionReader.readAction()
    }
}
