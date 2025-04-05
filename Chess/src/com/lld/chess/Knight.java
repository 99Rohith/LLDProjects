
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int posX, int posY, boolean isWhite) {
        super(posX, posY, isWhite, 'N');
    }

    @Override
    protected boolean isValidMove(int posX, int posY, ArrayList<ArrayList<Piece>> board) {
        if (!this.isValid(posX, posY)) return false;
        if (posX == this.getX() + 2 && posY == this.getY() + 1) return true;
        if (posX == this.getX() + 2 && posY == this.getY() - 1) return true;
        if (posX == this.getX() + 1 && posY == this.getY() + 2) return true;
        if (posX == this.getX() + 1 && posY == this.getY() - 2) return true;
        if (posX == this.getX() - 2 && posY == this.getY() + 1) return true;
        if (posX == this.getX() - 2 && posY == this.getY() - 1) return true;
        if (posX == this.getX() - 1 && posY == this.getY() + 2) return true;
        if (posX == this.getX() - 1 && posY == this.getY() - 2) return true;
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
