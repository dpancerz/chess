package com.dpancerz.chess

enum class ChessPieceType(val acronym: String, val pieceFun: Function1<Piece.Color, Piece>) {
    KING("K", {color -> King(color)}),
    QUEEN("Q", {color -> Queen(color)}),
    ROOK("R", {color -> Rook(color)}),
    BISHOP("B", {color -> Bishop(color)}),
    KNIGHT("N", {color -> Knight(color)}),
    PAWN("", {color -> Pawn(color)});

    companion object {
        private val map = ChessPieceType.values().associateBy(ChessPieceType::acronym)

        fun forAcronym(acronym: String): ChessPieceType {
            return map[acronym] ?: throw Exception()
        }
    }
}