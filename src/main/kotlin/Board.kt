package com.dpancerz.chess

import java.lang.RuntimeException

abstract class Board(private val size: Int) {
    protected val squares = HashMap<String, Square>()
    protected val pieces = HashSet<PieceOnBoard>()
    internal val firstColumnAsInt = 'A'.toInt()

    init {
        for (c in 0 until size) {
            for (r in 1..size) {
                val col = toColumn(c)
                val key = col.toString() + r.toString()
                squares[key] = Square(this, col, r)
            }
        }
    }

    fun square(col: Char, row: Int): Square {
        val safeCol = col.toUpperCase()
        if (outsideBounds(safeCol, row)) {
            throw SquareOutsideBoundsException("wrong 'square()' input- '$col$row'")
        }
        val squareId = safeCol.toString() + row.toString()
        return squares.getOrElse(squareId)
                { throw Exception("can't find square '$squareId'") }
    }

    fun addPieceOn(square: Square, piece: Piece) {
        pieces.add(PieceOnBoard(piece, square))
        square.setPiece(piece)
    }

    fun clear() {
        squares.forEach { it.value.clearHeldPiece() }
    }

    private fun outsideBounds(col: Char, row: Int): Boolean {
        val colAsNumber = toNumber(col)
        return row > size
                || row < 1
                || colAsNumber > size
                || colAsNumber < 1
    }

    internal fun toNumber(col: Char): Int {
        return col.toInt() + 1 - firstColumnAsInt
    }

    internal fun toColumn(col: Int): Char {
        return (col + firstColumnAsInt).toChar()
    }

    abstract fun isLegal(move: Move): Boolean
    abstract fun getStateAfter(move: Move): MoveType

    class SquareOutsideBoundsException(s: String) : RuntimeException(s)
}