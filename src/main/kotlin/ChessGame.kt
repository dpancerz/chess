package com.dpancerz.chess

class ChessGame(
    private val board: ChessBoard = ChessBoard(),
    private val translator: ChessTranslator = ChessTranslator(),
    positionSetter: ChessPositionSetter = ChessPositionSetter(board, translator)
) {
    private var toMove: Piece.Color = Piece.Color.WHITE //TODO use and toggle

    init {
        positionSetter.initializeBoard()
    }

    fun move(move: String): ExecutedMove {
        val moveDto = translator.parseMove(move)
        val pieceOnBoard = findPieceOnBoard(moveDto)
        val target = moveDto.target
        return move(pieceOnBoard, board.square(target.column, target.row))
    }

    private fun findPieceOnBoard(moveDto: MoveData): PieceOnBoard {
        val source = moveDto.source
        if (source != null) {
            return board.piece(moveDto.piece, source.column, source.row)
        }
        val target = moveDto.target
        val square = board.square(target.column, target.row)
        val canReach = board.getPiecesThatCanReach(square, moveDto.piece)
        try {
            return canReach.first()
        } catch (e: Exception) {
            throw Exception("no piece \'${moveDto.piece}\' found that could reach ${moveDto.target}")
        }
    }

    fun move(piece: PieceOnBoard, target: Square): ExecutedMove {
        return Move(piece, target).execute()
    }
}