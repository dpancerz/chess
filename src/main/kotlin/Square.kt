package com.dpancerz.chess

class Square(val board: Board, private val col: Char, private val row: Int, private var heldPiece: PieceOnBoard? = null) {
    val color: Color = color()
    fun plus(columns: Int, rows: Int): Square = board.square(col+ columns, row + rows)

    private fun color(): Color {
        val rowAndColSum = (board.toNumber(col) + row) % 2
        return if (rowAndColSum == 0) Color.BLACK
        else Color.WHITE
    }

    fun isFree(): Boolean {
        return heldPiece == null
    }

    fun isEmpty(): Boolean {
        return isFree()
    }

    fun clearHeldPiece() {
        heldPiece?.capture()
        heldPiece = null
    }

    fun hasPieceCapturableBy(piece: Piece): Boolean {
        return heldPiece?.canBeCaptured() ?: false
                && heldPiece?.color != piece.color
    }

    enum class Color {
        WHITE, BLACK
    }
}