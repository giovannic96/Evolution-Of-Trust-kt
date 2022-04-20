package com.tw.doubles

import com.tw.core.player.Action
import com.tw.core.player.Player
import com.tw.utils.PlayerConstants.DEFAULT_ACTION
import java.util.*

class PlayerSpy : Player {
    var actions: Queue<Action> = LinkedList(listOf())
    var scores = mutableListOf<Int>()

    override fun getName(): String {
        return ""
    }

    override fun getScore(): Int {
        return scores.sum()
    }

    override fun doAction(): Action {
        return actions.poll() ?: DEFAULT_ACTION
    }

    override fun updateScore(amount: Int) {
        scores.add(amount)
    }

    override fun determineOpponentLastActionByRoundScore(roundScore: Int): Action? {
        return null
    }
}
