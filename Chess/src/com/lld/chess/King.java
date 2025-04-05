
import java.util.ArrayList;


public class King extends Piece {
    public King(int posX, int posY, boolean isWhite) {
        super(posX, posY, isWhite, 'K');
    }

    @Override
    protected boolean isValidMove(int posX, int posY, ArrayList<ArrayList<Piece>> board) {
        if (!this.isValid(posX, posY)) return false;
        if (posX == this.getX() && posY == this.getY()) return false;
        if (Math.abs(posY - this.getY()) >= 2 || Math.abs(posX - this.getX()) >= 2) return false;
        return true;
    }

    @Override
    public boolean move(int posX, int posY, ArrayList<ArrayList<Piece>> board) {
        if (!this.isValidMove(posX, posY, board)) return false;
        this.setX(posX);
        this.setY(posY);
        return true;
    }
}
