package com.dpancerz.chess

import org.junit.Test

class GameTests {
    val game = ChessGame()

    @Test
    fun shouldCorrectlyExecuteMoves() {
        game.move("e4")
        game.move("d4")
        game.move("Nf3")
        game.move("Nc6")

        stateOfTheBoardIs("""
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

    private fun stateOfTheBoardIs(board: String) {
        board.trimIndent()
    }
}