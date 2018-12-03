package com.dpancerz.chess

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BoardTests {
    private val chessboard = ChessBoard()

    @Test
    fun shouldCalculateColorsCorrectly() {
        val A1 = chessboard.square('A', 1)
        val A2 = chessboard.square('A', 2)
        val B2 = chessboard.square('B', 2)
        val E4 = chessboard.square('E', 4)

        assertEquals(A1.color, Square.Color.BLACK)
        assertEquals(A2.color, Square.Color.WHITE)
        assertEquals(B2.color, Square.Color.BLACK)
        assertEquals(E4.color, Square.Color.WHITE)
    }

    @Test fun shouldConvertSixtyFiveToUpperCaseA() = assertEquals('A', 65.toChar())
}
