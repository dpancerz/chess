# chess
CHESS

It's supposed to be a middle layer between GUIs and actual chess engines.


For ease of use DSL created and understood by the application:

        |♜|  |♝|♛|♚|♝|♞|♜|
        |♟|♟|♟|♟|  |♟|♟|♟|
        |  | |♞|  |  |  |  |  |
        |  | |  |  |♟|  |  |  |
        |  | |  |  |♙|  |  |  |
        |  | |  |  |  |♘|  |  |
        |♙|♙|♙|♙|  |♙|♙|♙|
        |♖|♘|♗|♕|♔|♗|  |♖|

also, you can comminicate with the app state using algebraic notation (currently English only:

ChessGame game = ChessGame()
game.move("d4")
game.move("Nf6")

etc.
