package com.dpancerz.chess

abstract class ChessPiece(override var color: Piece.Color, private val type: ChessPieceType) : AbstractPiece(color) {
    override fun type(): String {
        return type.acronym
    }

    override fun toString(): String {
        return "ChessPiece(type=$type, color=$color)"
    }
}

data class King(override var color: Piece.Color) : ChessPiece(color, ChessPieceType.KING) {
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

data class Queen(override var color: Piece.Color) : CapturablePiece(ChessPieceType.QUEEN, color) {
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

data class Rook(override var color: Piece.Color) : CapturablePiece(ChessPieceType.ROOK, color) {
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

data class Bishop(override var color: Piece.Color) : CapturablePiece(ChessPieceType.BISHOP, color) {
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

data class Knight(override var color: Piece.Color) : CapturablePiece(ChessPieceType.KNIGHT, color) {
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

data class Pawn(override var color: Piece.Color) : CapturablePiece(ChessPieceType.PAWN, color) {
    val isWhite = color == Piece.Color.WHITE
    private val vertically = if (isWhite) 1 else -1
    private val startingRow = if (isWhite) 2 else 7
    private val enPassantRow = if (isWhite) 5 else 4
    private val moves: Set<PossibleMove> = setOf(
        PossibleMove(this, 0, vertically),
        PossibleMove(this, 0, 2 * vertically, startingRow)//starting move
    )
    private val captures: Set<PossibleMove> = setOf(
        PossibleMove(this, 1, vertically),
        PossibleMove(this, -1, vertically),
        PossibleMove(this, 1, vertically, enPassantRow, null), //TODO correct en passant
        PossibleMove(this, -1, vertically, enPassantRow, null) //TODO
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