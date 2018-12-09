package com.dpancerz.chess

class Move(val piece: PieceOnBoard, var target: Square) {
    fun execute(): ExecutedMove {
        if (isLegal()) {
            piece.moveTo(target)
            return ExecutedMove(piece, piece.standingOn, piece.getBoard().getStateAfter(this))
        }
        throw Exception("can't execute move $piece -> $target")
    }

    private fun isLegal(): Boolean {
        return piece.getBoard() == target.board
                && (canGoNormally() || canCapture())
                && piece.getBoard().isLegal(this)
    }

    private fun canGoNormally() = piece.isMovementLegal(this) && target.isEmpty()

    private fun canCapture() = piece.isCapturingMovementLegal(this)
            && target.hasPieceCapturableBy(this.piece)

    override fun toString(): String {
        return "Move(piece=${piece.piece.type()}, target=$target)"
    }

}
