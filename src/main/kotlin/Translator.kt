package com.dpancerz.chess

class Translator {
    private val pieces: Map<String, Function<AbstractPiece>> = hashMapOf(
        "♜" to { sq: Square -> Rook(AbstractPiece.Color.WHITE, sq) },
        "♞" to { sq: Square -> Knight(AbstractPiece.Color.WHITE, sq) },
        "♝" to { sq: Square -> Bishop(AbstractPiece.Color.WHITE, sq) },
        "♛" to { sq: Square -> Queen(AbstractPiece.Color.WHITE, sq) },
        "♚" to { sq: Square -> King(AbstractPiece.Color.WHITE, sq) },
        "♟" to { sq: Square -> Pawn(AbstractPiece.Color.WHITE, sq) },
        "♙" to { sq: Square -> Pawn(AbstractPiece.Color.BLACK, sq) },
        "♖" to { sq: Square -> Rook(AbstractPiece.Color.BLACK, sq) },
        "♘" to { sq: Square -> Knight(AbstractPiece.Color.BLACK, sq) },
        "♗" to { sq: Square -> Bishop(AbstractPiece.Color.BLACK, sq) },
        "♕" to { sq: Square -> Queen(AbstractPiece.Color.BLACK, sq) },
        "♔" to { sq: Square -> King(AbstractPiece.Color.BLACK, sq) }
    )
    val symbols = pieces.keys

    fun toPiece(symbol: String): Function<AbstractPiece> {
        return pieces[symbol] ?: throw Exception()
    }
}