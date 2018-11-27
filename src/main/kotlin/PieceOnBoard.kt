package com.dpancerz.chess

class PieceOnBoard(val piece: AbstractPiece, var standingOn: Square) : Piece by piece {
    private var captured: Boolean = false

    fun getBoard(): Board {
        return standingOn.board
    }

    fun moveTo(square: Square) {
        standingOn.clearHeldPiece()
        standingOn = square
    }

    fun capture() {
        captured = true
    }

    fun isMovementLegal(move: Move): Boolean {
        return possiblyLegalMoves()
            .any { it -> matches(move, it) }
    }

    fun isCapturingMovementLegal(move: Move): Boolean {
        return possibleCaptures()
            .any { it -> matches(move, it) }
    }

    private fun matches(move: Move, allowed: PossibleMove): Boolean {
        return move.target == this.standingOn
            .plus(allowed.horizontally, allowed.vertically)
    }
}