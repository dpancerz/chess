package com.dpancerz.chess

abstract class Board(private val size: Int) {
    protected val squares = HashMap<String, Square>()
    protected val pieces = HashSet<PieceOnBoard>()
    init {
        for (c in 1..size) {
            for (r in 1..size) {
                val col = toColumn(c)
                val key = col.toString() + r.toString()
                squares[key] = Square(this, col, r)
            }
        }
    }

    fun square(col: Char, row: Int): Square {
        if (outsideBounds(col, row)) {
            throw Exception("wrong 'square()' input")
        }
        return squares.getOrElse(col.toString() + row.toString())
                { Square(this,'#', -1) }
    }

    fun addPieceOn(square: Square, piece: Piece) {
        pieces.add(PieceOnBoard(piece, square))
    }

    fun clear() {
        squares.forEach { it.value.clearHeldPiece() }
    }

    private fun outsideBounds(col: Char, row: Int) = row > size || toNumber(col) > size

    internal fun toNumber(col: Char): Int {
        return col.toUpperCase().toInt() + 1 - 'A'.toInt()
    }

    private fun toColumn(col: Int): Char {
        return (col + 64).toChar()
    }

    abstract fun isLegal(move: Move): Boolean
    abstract fun getStateAfter(move: Move): MoveType
}