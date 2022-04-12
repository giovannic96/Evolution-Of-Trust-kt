package com.tw.game.player

class ConsolePlayer(
    name: String,
    score: Int,
    private val actionReader: ActionReader
) : AbstractPlayer(name, score) {

    override fun doAction(): Action {
        return actionReader.readAction()
    }
}
