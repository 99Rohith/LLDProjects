
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(int posX, int posY, boolean isWhite) {
        super(posX, posY, isWhite, 'R');
    }

    @Override
    protected boolean isValidMove(int posX, int posY, ArrayList<ArrayList<Piece>> board) {
        if (!this.isValid(posX, posY)) return false;
        if (posX != this.getX() && posY != this.getY()) return false;
        int i = this.getX() - 1, j = this.getY();
        while (this.isValid(i, j)) {
            if (i == posX && j == posY) return true;
            if (board.get(i).get(j) != null) break;
            --i;
        }

        i = this.getX() + 1;
        j = this.getY();
        while (this.isValid(i, j)) {
            if (i == posX && j == posY) return true;
            if (board.get(i).get(j) != null) break;
            ++i;
        }

        i = this.getX();
        j = this.getY() - 1;
        while (this.isValid(i, j)) {
            if (i == posX && j == posY) return true;
            if (board.get(i).get(j) != null) break;
            --j;
        }

        i = this.getX();
        j = this.getY() + 1;
        while (this.isValid(i, j)) {
            if (i == posX && j == posY) return true;
            if (board.get(i).get(j) != null) break;
            ++j;
        }

        return false;
    }

    @Override
    public boolean move(int posX, int posY, ArrayList<ArrayList<Piece>> board) {
        if (!this.isValidMove(posX, posY, board)) return false;
        this.setX(posX);
        this.setY(posY);
        return true;
    }
}
