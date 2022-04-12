package com.tw.game.player

interface Player {

    fun getName(): String
    fun getScore(): Int
    fun doAction(): Action
    fun updateScore(amount: Int)
}
