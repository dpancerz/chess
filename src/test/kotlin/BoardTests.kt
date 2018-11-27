package com.dpancerz.chess

import org.junit.Test

class BoardTests {
    private val chessboard = ChessBoard()
    @Test
    fun shouldCalculateColorsCorrectly() {
        val A1 = chessboard.square('A', 1)
        val A2 = chessboard.square('A', 2)
        val B2 = chessboard.square('B', 2)
        val E4 = chessboard.square('E', 4)

        assert(A1.color == Square.Color.BLACK)
        assert(A2.color == Square.Color.WHITE)
        assert(B2.color == Square.Color.BLACK)
        assert(E4.color == Square.Color.WHITE)
    }

    @Test fun shouldConvertSixtyFiveToUpperCaseA() = assert('A' == 65.toChar())
}
