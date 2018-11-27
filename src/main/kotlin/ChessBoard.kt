package com.dpancerz.chess

class ChessBoard(val translator: Translator = Translator()) : Board(8) {
    init {
        // TODO call translator
    }
    val startingPosition = """
        |♜|♞|♝|♛|♚|♝|♞|♜|
        |♟|♟|♟|♟|♟|♟|♟|♟|
        |  | |  |  | |  |  |  |
        |  | |  |  | |  |  |  |
        |  | |  |  | |  |  |  |
        |  | |  |  | |  |  |  |
        |♙|♙|♙|♙|♙|♙|♙|♙|
        |♖|♘|♗|♕|♔|♗|♘|♖|
        """.trimIndent()

    override fun getStateAfter(move: Move): MoveType {
        val movedPiece = move.piece
        val kingOfDifferentColor = getPieces("K")
            .filter {
                it.color != movedPiece.color
            }[0]
        return if (!isAttacked(kingOfDifferentColor)) MoveType.REGULAR
            else MoveType.REGULAR //FIXME doesn't say 'checkmate'
    }

    private fun getPieces(type: String): List<PieceOnBoard> {
        return pieces
            .filter { it.type() == type }
    }

    override fun isLegal(move: Move): Boolean { //TODO
        val color = move.piece.color
        return isChecked(color) // wouldBeCheckedAfterMove
    }

    fun isChecked(color: Piece.Color): Boolean {
        return getPieces("K")
            .filter { it -> it.color == color }
            .any { it -> isAttacked(it) }
    }

    private fun isAttacked(piece: PieceOnBoard): Boolean { //TODO
        return false
    }

}
