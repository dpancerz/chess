package com.dpancerz.chess

import java.lang.Exception

data class Square(
    val board: Board,
    private val col: Char,
    internal val row: Int,
    private var heldPiece: PieceOnBoard? = null,
    private val attackedBy: List<PieceOnBoard> = ArrayList()
) {
    val color: Color = color()
    fun plus(columns: Int, rows: Int): Square = board.square(col + columns, row + rows)

    fun isFree(): Boolean {
        return heldPiece == null
    }

    fun isEmpty(): Boolean {
        return isFree()
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    fun setPiece(piece: Piece) {
        if (isNotEmpty()) throw Exception()
        heldPiece = PieceOnBoard(piece, this)
    }

    fun capturePiece() {
        heldPiece?.capture()
        heldPiece = null
    }

    fun clearHeldPiece() {
        heldPiece?.clear()
        heldPiece = null
    }

    fun hasPieceCapturableBy(piece: Piece): Boolean {
        return heldPiece?.canBeCaptured() ?: false
                && heldPiece?.color != piece.color
    }

    private fun color(): Color {
        val rowAndColSum = (board.toNumber(col) + row) % 2
        return if (rowAndColSum == 0) Color.BLACK
        else Color.WHITE
    }

    enum class Color {
        WHITE, BLACK
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Square) return false
        return this.col == other.col
                && this.row == other.row
    }

    override fun toString(): String {
        return "Square('$col$row', piece=${heldPiece?.piece})"
    }

}