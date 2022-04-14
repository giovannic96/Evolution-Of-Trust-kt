package com.tw.core.player

class ChoicePlayer(
    name: String,
    score: Int,
    private val lastActionWrapper: LastActionWrapper,
    private val actionReader: ActionReader
) : AbstractPlayer(name, score) {

    override fun doAction(): Action {
        val action = actionReader.readAction()
        lastActionWrapper.setLastAction(action)
        return action
    }
}
