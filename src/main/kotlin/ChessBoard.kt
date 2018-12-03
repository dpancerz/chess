package com.dpancerz.chess

class ChessBoard : Board(8) {
    override fun getStateAfter(move: Move): MoveType {
        val movedPiece = move.piece
        val kingOfDifferentColor = getPieces("K")
            .filter {
                it.color != movedPiece.color
            }[0]
        return if (!isAttacked(kingOfDifferentColor)) MoveType.REGULAR
            else MoveType.CHECK //FIXME doesn't say 'checkmate'
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

    fun piece(piece: Piece, column: Char, row: Int): PieceOnBoard {
        return pieces.filter { it.piece == piece }
            .first { it.standingOn == square(column, row) }
    }

    private fun getPieces(type: String): List<PieceOnBoard> {
        return pieces
            .filter { it.type() == type }
    }

    private fun isAttacked(piece: PieceOnBoard): Boolean { //TODO
        return false
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ChessBoard) return false
        return other.pieces == this.pieces
    }

    fun getPiecesThatCanReach(square: Square, piece: Piece): List<PieceOnBoard> {
        return if (piece.hasColor()) {
            pieces.filter { it.piece == piece }
        } else {
            pieces.filter { it -> samePieceTypes(it, piece) }
        }.filter { it.isMovementLegal(square) }
    }

    private fun samePieceTypes(onBoard: PieceOnBoard, piece: Piece): Boolean {
        return onBoard.piece.type() == piece.type()
    }
}
