package com.dpancerz.chess

class PieceOnBoard(val piece: Piece, var standingOn: Square) : Piece by piece {
    private var captured: Boolean = false
    private var removed: Boolean = false

    fun getBoard(): Board {
        return standingOn.board
    }

    fun moveTo(square: Square) {
        //TODO piece.removeCurrentAttacks()
        standingOn.clearHeldPiece()
        standingOn = square
        //TODO piece.addAttacks()
    }

    fun capture() {
        captured = true
    }

    fun clear() {
        removed = true
    }

    fun isMovementLegal(move: Move): Boolean {
        return isMovementLegal(target(move))
    }

    fun isMovementLegal(target: Square): Boolean {
        return contains(target, possiblyLegalMoves())
    }

    fun contains(target: Square, moves: Set<PossibleMove>): Boolean {
        return moves.any { it -> matches(target, it) }
    }

    fun isCapturingMovementLegal(move: Move): Boolean {
        return isCapturingMovementLegal(target(move))
    }

    fun isCapturingMovementLegal(target: Square): Boolean {
        return contains(target, possibleCaptures())
    }

    private fun matches(target: Square, allowed: PossibleMove): Boolean {
        val targetMatches = target == this.standingOn
            .plus(allowed.horizontally, allowed.vertically)
        val isRowRestricted = allowed.onlyFromRow != null
        val rowsMatch = allowed.onlyFromRow == this.standingOn.row
        return targetMatches && (isRowRestricted.not()
                || isRowRestricted && rowsMatch)
    }

    private fun target(move: Move) = move.target

    override fun equals(other: Any?): Boolean {
        if (other !is PieceOnBoard) return false
        return this.piece == other.piece
                && this.standingOn == other.standingOn
    }
}