package com.dpancerz.chess

object TempChessApplication {

    @JvmStatic
    fun main(args: Array<String>)  {
        val board = ChessBoard()
        board.square('A', 1).isFree()
    }
}