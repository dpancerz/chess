package com.dpancerz.chess

class ChessPositionSetter(
    private val board: ChessBoard,
    private val translator: ChessTranslator,
    private val position: String? = null
) {
    fun initializeBoard() {
        setBoardPosition(position ?: startingPosition)
    }

    fun setBoardPosition(positionToSet: String) {
        val lines = positionToSet.lines()
        if (lines.size != 8) throw Exception()

        (0..7)
            .map { row -> RowData(8 - row, lines[row]) }
            .flatMap { rows -> toSquareData(rows) }
            .forEach { setPieceOnBoard(it) }
    }

    private fun setPieceOnBoard(data: PieceOnBoardData) {
        if (data.pieceSymbol.isBlank()) return
        val square: Square = board.square(data.squareCol, data.squareRow)
        val piece: Piece = translator.fromSymbol(data.pieceSymbol)
        board.addPieceOn(square, piece)
    }

    private fun toSquareData(rowData: RowData): List<PieceOnBoardData> {
        return rowData.rowValue
            .split("|")
            .drop(1)
            .take(8)
            .mapIndexed { i, piece -> PieceOnBoardData(rowData.rowNumber,
                board.toColumn(i),
                piece) }
    }

    companion object {
        val startingPosition = """
        |♜|♞|♝|♛|♚|♝|♞|♜|
        |♟|♟|♟|♟|♟|♟|♟|♟|
        |  | |  |  | |  |  |  |
        |  | |  |  | |  |  |  |
        |  | |  |  | |  |  |  |
        |  | |  |  | |  |  |  |
        |♙|♙|♙|♙|♙|♙|♙|♙|
        |♖|♘|♗|♕|♔|♗|♘|♖|
        """.trimIndent()
    }
}

private data class RowData(val rowNumber: Int, val rowValue: String)
private data class PieceOnBoardData(val squareRow: Int, val squareCol: Char, val pieceSymbol: String)