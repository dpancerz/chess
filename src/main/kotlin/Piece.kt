package com.dpancerz.chess

interface Piece {
    val color: Piece.Color
    fun possiblyLegalMoves(): Set<PossibleMove>
    fun possibleCaptures(): Set<PossibleMove> = possiblyLegalMoves()
    fun type(): String
    fun canBeCaptured() : Boolean

    fun canBeCapturedBy(another: Piece): Boolean {
        return canBeCaptured()
                && isDifferentColorAs(another)
    }

    fun isDifferentColorAs(another: Piece): Boolean {
        return this.color != another.color
    }

    enum class Color {
        BLACK,
        WHITE
    }
}