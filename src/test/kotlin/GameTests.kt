package com.dpancerz.chess

import org.junit.jupiter.api.Test

class GameTests {
    val game = ChessGame()

    @Test
    fun shouldCorrectlyExecuteFirstMoves() {
        game.move("e4")
        game.move("d4")
        game.move("Nf3")
        game.move("Nc6")

        thenStateOfTheBoardIs("""
        |♜|  |♝|♛|♚|♝|♞|♜|
        |♟|♟|♟|♟|  |♟|♟|♟|
        |  | |♞|  |  |  |  | |
        |  | |  |  |♟|  |  | |
        |  | |  |  |♙|  |  | |
        |  | |  |  |  |♘|  | |
        |♙|♙|♙|♙|  |♙|♙|♙|
        |♖|♘|♗|♕|♔|♗|  |♖|
        """)
    }

    @Test
    fun shouldAllowCapturingPieces() {
        throw Exception("not implemented")
    }

    @Test
    fun shouldAllowEnPassant() {
        givenStateOfTheBoard("""
        |♜|  |♝|♛|♚|  |♞|♜|
        |♟|♟|♟|♟|  |♟|♟|♟|
        |  | |♞|  |  |  |  | |
        |  | |♝| ♙|♟|  |  | |
        |  | |  |  |  |  |  | |
        |  | |  |  |  |  |  | |
        |♙|♙|♙|  |♙|♙|♙|♙|
        |♖|♘|♗|♕|♔|♗|♘|♖|
        """)

        //when
        game.move("dxe6")

        thenStateOfTheBoardIs("""
        |♜|  |♝|♛|♚|  |♞|♜|
        |♟|♟|♟|♟|  |♟|♟|♟|
        |  | |♞|  |♙|  |  | |
        |  | |♝|  |  |  |  | |
        |  | |  |  |  |  |  | |
        |  | |  |  |  |  |  | |
        |♙|♙|♙|  |♙|♙|♙|♙|
        |♖|♘|♗|♕|♔|♗|♘|♖|
        """)
    }

    private fun givenStateOfTheBoard(board: String) {
        board.trimIndent()
    }

    private fun thenStateOfTheBoardIs(board: String) {
        board.trimIndent()
    }
}