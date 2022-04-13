package com.tw.core.player

abstract class AbstractPlayer(
    private val name: String,
    private var score: Int,
) : Player {

    override fun getName(): String {
        return this.name
    }

    override fun getScore(): Int {
        return this.score
    }

    override fun updateScore(amount: Int) {
        this.score += amount
    }
}