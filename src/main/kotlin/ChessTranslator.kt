package com.dpancerz.chess

class ChessTranslator {
    private val chessBoardSize = 8
    private val firstColumn = 'A'
    private val whiteRook = Rook(Piece.Color.WHITE)
    private val whiteKnight = Knight(Piece.Color.WHITE)
    private val whiteBishop = Bishop(Piece.Color.WHITE)
    private val whiteQueen = Queen(Piece.Color.WHITE)
    private val whiteKing = King(Piece.Color.WHITE)
    private val whitePawn = Pawn(Piece.Color.WHITE)
    private val blackPawn = Pawn(Piece.Color.BLACK)
    private val blackRook = Rook(Piece.Color.BLACK)
    private val blackKnight = Knight(Piece.Color.BLACK)
    private val blackBishop = Bishop(Piece.Color.BLACK)
    private val blackQueen = Queen(Piece.Color.BLACK)
    private val blackKing = King(Piece.Color.BLACK)
    private val pieces: Map<String, Piece> = hashMapOf(
        "♜" to blackRook,
        "♞" to blackKnight,
        "♝" to blackBishop,
        "♛" to blackQueen,
        "♚" to blackKing,
        "♟" to blackPawn,
        "♙" to whitePawn,
        "♖" to whiteRook,
        "♘" to whiteKnight,
        "♗" to whiteBishop,
        "♕" to whiteQueen,
        "♔" to whiteKing
    )
    val symbols = pieces.keys
    val acronyms = ChessPieceType.values().map { it.acronym }
    private val noColor = Piece.Color.NOT_AVAILABLE

    fun parseMove(move: String): MoveData {
        val startsWithPiece = isSymbolOrAcronym(move[0].toString())
        val start = if (startsWithPiece) 1 else 0

        val piece = extractPiece(move, startsWithPiece)
        val firstSquare = extractSquare(move, start) ?: throw Exception()

        return if (hasSecondSquare(move, startsWithPiece)) {
            val secondSquare = extractSquare(move, start + 2) ?: throw Exception()
            MoveData(piece, secondSquare, firstSquare)
        } else {
            MoveData(piece, firstSquare, null)
        }
    }

    fun fromSymbol(symbol: String): Piece {
        return pieces[symbol] ?: throw Exception()
    }

    private fun extractSquare(move: String, start: Int): SquareData? {
        val square = move.substring(start, start + 2)
        val (column, row, isSquare) = isSquare(square)
        return if (isSquare) {
            SquareData(column, row)
        } else null
    }

    private fun hasSecondSquare(move: String, startsWithPiece: Boolean): Boolean {
        val start = if (startsWithPiece) 3 else 2
        if (move.length <= start) {
            return false
        }
        val square = move.substring(start, start + 2)
        val (_, _, isSquare) = isSquare(square)
        return isSquare
    }

    private fun isSquare(square: String): Triple<Char, Int, Boolean> {
        val column = square[0]
        val row = Character.getNumericValue(square[1])
        val isSquare = isSquare(column, row)
        return Triple(column, row, isSquare)
    }

    private fun isSquare(column: Char, row: Int) = isColumn(column) && isRow(row)

    private fun isRow(row: Int): Boolean {
        return row in 1..chessBoardSize
    }

    private fun isColumn(column: Char): Boolean {
        val columnAsAsciiInt = column.toUpperCase().toInt()
        return columnAsAsciiInt in firstColumn.toInt() until firstColumn.toInt() + chessBoardSize
    }

    private fun isSymbolOrAcronym(symbol: String): Boolean {
        return isSymbol(symbol) || isAcronym(symbol)
    }

    private fun extractPiece(move: String, startsWithPiece: Boolean): Piece {
        if (!startsWithPiece) {
            return Pawn(noColor)
        }
        val symbol = move[0].toString()
        return when {
            isSymbol(symbol) -> fromSymbol(symbol)
            isAcronym(symbol) -> fromAcronym(symbol)(noColor)
            else -> throw Exception("found no piece for input \'$symbol\'")
        }
    }

    private fun isAcronym(symbol: String): Boolean {
        return acronyms.any { it == symbol }
    }

    private fun isSymbol(symbol: String): Boolean {
        return symbols.any { it == symbol }
    }

    private fun fromAcronym(acronym: String) : Function1<Piece.Color, Piece> {
        return ChessPieceType.forAcronym(acronym).pieceFun
    }
}
data class SquareData(val column: Char, val row: Int)
data class MoveData(val piece: Piece, val target: SquareData, val source: SquareData? = null)