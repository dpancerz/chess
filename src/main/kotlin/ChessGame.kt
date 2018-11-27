package com.dpancerz.chess

class ChessGame(val board: ChessBoard = ChessBoard(),
                val translator: Translator = Translator(),
                val moveParser: MoveParser = MoveParser()) {

    private var toMove: AbstractPiece.Color = AbstractPiece.Color.WHITE
    init {
        board.startingPosition
    }

/*    fun move(move: String) {
        extractPiece(move)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun extractPiece(move: String) {
        move
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
*/
    fun move(piece: ChessPiece, target: Square) {
        Move(piece, target).execute()
    }
}