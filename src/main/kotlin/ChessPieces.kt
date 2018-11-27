package com.dpancerz.chess

abstract class ChessPiece(color: Piece.Color, private val type: ChessPieceType) : AbstractPiece(color) {
    override fun type(): String {
        return type.acronym
    }
}

class King(color: Piece.Color) : ChessPiece(color, ChessPieceType.KING) {
    private val moves: Set<PossibleMove> = setOf<PossibleMove>()
        .plus(diagonally(this, 1))
        .plus(alongTheLines(this, 1))

    override fun possiblyLegalMoves(): Set<PossibleMove> {
        return moves
    }

    override fun canBeCaptured(): Boolean {
        return false
    }
}

abstract class CapturablePiece(pieceType: ChessPieceType, color: Piece.Color) : ChessPiece(color, pieceType) {
    override fun canBeCaptured(): Boolean {
        return true
    }
}

class Queen(color: Piece.Color) : CapturablePiece(ChessPieceType.QUEEN, color) {
    private val moves: Set<PossibleMove>

    init {
        var res: Set<PossibleMove> = mutableSetOf()
        for (length in 1..7) {
            res = res.plus(diagonally(this, length))
                .plus(alongTheLines(this, length))
        }
        moves = res
    }

    override fun possiblyLegalMoves(): Set<PossibleMove> {
        return moves
    }
}

class Rook(color: Piece.Color) : CapturablePiece(ChessPieceType.ROOK, color) {
    private val moves: Set<PossibleMove>

    init {
        var res: Set<PossibleMove> = mutableSetOf()
        for (length in 1..7) {
            res = res.plus(alongTheLines(this, length))
        }
        moves = res
    }

    override fun possiblyLegalMoves(): Set<PossibleMove> {
        return moves
    }
}

class Bishop(color: Piece.Color) : CapturablePiece(ChessPieceType.BISHOP, color) {
    private val moves: Set<PossibleMove>

    init {
        var res: Set<PossibleMove> = mutableSetOf()
        for (length in 1..7) {
            res = res.plus(diagonally(this, length))
        }
        moves = res
    }

    override fun possiblyLegalMoves(): Set<PossibleMove> {
        return moves
    }
}

class Knight(color: Piece.Color) : CapturablePiece(ChessPieceType.KNIGHT, color) {
    private val moves: Set<PossibleMove> = setOf(
        PossibleMove(this, 1, 2),
        PossibleMove(this, -1, 2),
        PossibleMove(this, -1, -2),
        PossibleMove(this, 1, -2),
        PossibleMove(this, 2, 1),
        PossibleMove(this, 2, -1),
        PossibleMove(this, -2, -1),
        PossibleMove(this, -2, 1)
    )

    override fun possiblyLegalMoves(): Set<PossibleMove> {
        return moves
    }
}

class Pawn(color: Piece.Color) : CapturablePiece(ChessPieceType.PAWN, color) {
    private val vertically = if (color == Piece.Color.WHITE) 1 else -1
    private val moves: Set<PossibleMove> = setOf(
        PossibleMove(this, 0, vertically)
    )
    private val captures: Set<PossibleMove> = setOf(
        PossibleMove(this, 1, vertically),
        PossibleMove(this, -1, vertically)
    )

    override fun possiblyLegalMoves(): Set<PossibleMove> {
        return moves
    }

    override fun possibleCaptures(): Set<PossibleMove> {
        return captures
    }
}

fun diagonally(piece: AbstractPiece, length: Int): Set<PossibleMove> {
    return setOf(
        PossibleMove(piece, length, length),
        PossibleMove(piece, -length, length),
        PossibleMove(piece, length, -length),
        PossibleMove(piece, -length, -length)
    )
}

fun alongTheLines(piece: AbstractPiece, length: Int): Set<PossibleMove> {
    return setOf(
        PossibleMove(piece, length, 0),
        PossibleMove(piece, -length, 0),
        PossibleMove(piece, 0, length),
        PossibleMove(piece, 0, -length)
    )
}