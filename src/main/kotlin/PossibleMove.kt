package com.dpancerz.chess

data class PossibleMove(
    val piece: AbstractPiece,
    val horizontally: Int,
    val vertically: Int,
    val onlyFromRow: Int? = null,
    val onlyIfPreviousMoveWas: PreviousMove? = null
)

data class PreviousMove(val piece: Piece, val fromRow: Int)