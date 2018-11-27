package com.dpancerz.chess

class ExecutedMove(val piece: PieceOnBoard,
                   val target: Square,
                   val type: MoveType = MoveType.REGULAR,
                   val strength: MoveStrength = MoveStrength.REGULAR)
