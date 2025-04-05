
import java.util.ArrayList;

public class ChessBoard {
    private final ArrayList<ArrayList<Piece>> mBoard = new ArrayList<>();
    private final User mUser1;
    private final User mUser2;
    private User mTurn;
    private User mWinner = null;

    public ChessBoard(User user1, User user2) {
        this.mUser1 = user1;
        this.mUser2 = user2;
        if (user1.getPickedWhite()) {
            mTurn = user1;
        } else {
            mTurn = user2;
        }

        for (int i=0;i<8;++i) {
            ArrayList<Piece> row = new ArrayList<>();
            for (int j=0;j<8;++j) {
                row.add(null);
            }

            mBoard.add(row);
        }

        mBoard.get(0).set(0, new Rook(0, 0, true));
        mBoard.get(0).set(1, new Knight(0, 1, true));
        mBoard.get(0).set(2, new Bishop(0, 2, true));
        mBoard.get(0).set(3, new King(0, 3, true));
        mBoard.get(0).set(4, new Queen(0, 4, true));
        mBoard.get(0).set(5, new Bishop(0, 5, true));
        mBoard.get(0).set(6, new Knight(0, 6, true));
        mBoard.get(0).set(7, new Rook(0, 7, true));

        for (int j=0;j<8;++j) {
            mBoard.get(1).set(j, new Pawn(1, j, true));
        }

        mBoard.get(7).set(0, new Rook(7, 0, false));
        mBoard.get(7).set(1, new Knight(7, 1, false));
        mBoard.get(7).set(2, new Bishop(7, 2, false));
        mBoard.get(7).set(3, new King(7, 3, false));
        mBoard.get(7).set(4, new Queen(7, 4, false));
        mBoard.get(7).set(5, new Bishop(7, 5, false));
        mBoard.get(7).set(6, new Knight(7, 6, false));
        mBoard.get(7).set(7, new Rook(7, 7, false));

        for (int j=0;j<8;++j) {
            mBoard.get(6).set(j, new Pawn(6, j, false));
        }
    }

    public void printBoard() {
        System.out.print("  ");
        for (int i=0;i<8;++i) {
            System.out.print(i + "  ");
        }

        System.out.println("");
        for (int i=0;i<8;++i) {
            System.out.print(i + " ");
            for (int j=0;j<8;++j) {
                if (this.mBoard.get(i).get(j) == null) {
                    System.out.print("__");
                } else {
                    if (this.mBoard.get(i).get(j).getWhite()) {
                        System.out.print("W");
                    } else {
                        System.out.print("B");
                    }

                    System.out.print(String.valueOf(this.mBoard.get(i).get(j).getLetter()));
                }

                System.out.print(" ");
            }

            System.out.println(i + "");
        }

        System.out.print("  ");
        for (int i=0;i<8;++i) {
            System.out.print(i + "  ");
        }

        System.out.println("");
        System.out.println("");
    }

    public boolean move(int curX, int curY, int destX, int destY) {
        if (this.mWinner != null) {
            System.err.println("Game already over");
            return false;
        }

        if (!isValid(curX, curY)) return false;
        if (!isValid(destX, destY)) return false;
        if (mBoard.get(curX).get(curY) == null) return false;
        if (mBoard.get(curX).get(curY).getWhite() != mTurn.getPickedWhite()) {
            System.err.println("Cannot touch other color piece");
            return false;
        }

        if (mBoard.get(curX).get(curY).move(destX, destY, mBoard)) {
            if (mBoard.get(destX).get(destY) instanceof King) {
                System.out.println(mTurn.getName() + " - Won the match, Congratulations");
                this.mWinner = mTurn;
            }

            mBoard.get(destX).set(destY, mBoard.get(curX).get(curY));
            mBoard.get(curX).set(curY, null);
            if (mTurn == mUser1) {
                mTurn = mUser2;
            } else {
                mTurn = mUser1;
            }

            return true;
        }

        System.err.println("Invalid move");
        return false;
    }

    private boolean isValid(int posX, int posY) {
        return (posX >= 0 && posX < 8 && posY >= 0 && posY < 8);
    }
}
