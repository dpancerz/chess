package com.dpancerz.chess

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

class ChessTranslatorTest {
    private val parser = ChessTranslator()
    private var move: MoveData? = null

    @Test
    fun shouldParseSymbolicFianchetto() {
        givenMoveSignature("♗b2")

        expectedMovedPieceIs(Bishop(Piece.Color.WHITE))
        andSourceSquareIs(null)
        andTargetSquareIs(SquareData('b',2))
    }

    @Test fun shouldParseSymbolic() {
        givenMoveSignature("♛e4")

        expectedMovedPieceIs(Queen(Piece.Color.BLACK))
        andSourceSquareIs(null)
        andTargetSquareIs(SquareData('e',4))
    }

    @Test fun shouldParsePawnMove() {
        givenMoveSignature("e4")

        expectedMovedPieceIs(Pawn(Piece.Color.NOT_AVAILABLE))
        andSourceSquareIs(null)
        andTargetSquareIs(SquareData('e',4))
    }

    @Test fun shouldParseAlgebraicWithLetterSymbol() {
        givenMoveSignature("Nf3")

        expectedMovedPieceIs(Knight(Piece.Color.NOT_AVAILABLE))
        andSourceSquareIs(null)
        andTargetSquareIs(SquareData('f',3))
    }

    @Test fun shouldParseAlgebraicWithSourceSquareLetterSymbol() {
        givenMoveSignature("Nf6e4")

        expectedMovedPieceIs(Knight(Piece.Color.NOT_AVAILABLE))
        andSourceSquareIs(SquareData('f',6))
        andTargetSquareIs(SquareData('e',4))
    }

    @Test fun shouldThrowExceptionOnUnparsableMove() {
        assertThrows<Exception> { givenMoveSignature("abc1") }
        
    }

    private fun givenMoveSignature(signature: String) {
        move = parser.parseMove(signature)
    }

    private inline fun expectedMovedPieceIs(piece: Piece) {
        assertEquals(move?.piece, piece,"moved piece mismatch")
    }

    private fun andSourceSquareIs(source: SquareData?) {
        assertEquals(move?.source, source, "source square mismatch")
    }

    private fun andTargetSquareIs(target: SquareData) {
        assertEquals(move?.target, target, "target square mismatch")
    }
}