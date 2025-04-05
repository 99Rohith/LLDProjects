/**
 * % javac -d ./build/ ./src/com/lld/chess/*.java
 * % cd build
 * build % java Main
 */

/**
 * A chess board has exactly 6 kinds of pieces:
 * K - king, Q- queen, R - rook,
 * B - bishop, H - knight (Horse) and P - pawn
 * Pieces are of white and black colour.
 * Pieces are arranged in 0th and 7th rows as follows,
 * "R","H","B","Q","K","B","H","R"
 * 1st and 6th rows have pawns.
 * Chess is played between two players.
 * A player may move their piece an empty cell or to a
 * position currently occupied by a piece of the opponent,
 * hence capturing the piece. Players can never capture their own piece.
 * The game starts with the player owning the white pieces making the first move.
 * After this, the players play alternate turns.
 * Game ends when King of either white or black is captured.
 */
public class Main {
    public static void main(String[] args) {
        User user1 = new User(true, "user1");
        User user2 = new User(false, "user2");
        ChessBoard chessBoard = new ChessBoard(user1, user2);
        chessBoard.printBoard();
        chessBoard.move(1, 3, 3, 3); // white
        chessBoard.move(6, 3, 4, 3); // black
        chessBoard.move(0, 3, 1, 3); // white
        chessBoard.move(7, 4, 4, 1); // black
        chessBoard.move(1, 3, 2, 3); // white
        chessBoard.move(4, 1, 2, 3); // black
        chessBoard.printBoard();
    }
}
