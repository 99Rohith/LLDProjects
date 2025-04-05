
import java.util.ArrayList;


public class Pawn extends Piece {
    private boolean isFirstMove = true;

    public Pawn(int posX, int posY, boolean isWhite) {
        super(posX, posY, isWhite, 'P');
    }

    @Override
    public boolean move(int posX, int posY, ArrayList<ArrayList<Piece>> board) {
        if (!this.isValidMove(posX, posY, board)) return false;
        this.isFirstMove = false;
        this.setX(posX);
        this.setY(posY);
        return true;
    }

    @Override
    protected boolean isValidMove(int posX, int posY, ArrayList<ArrayList<Piece>> board) {
        if (!this.isValid(posX, posY)) return false;
        if (this.getWhite() && posX <= this.getX()) return false;
        if (!this.getWhite() && posX >= this.getX()) return false;
        final boolean filled = (board.get(posX).get(posY) != null);
        if (filled) {
            if (Math.abs(posY - this.getY()) != 1) return false;
            if (Math.abs(posX - this.getX()) != 1) return false;
        } else {
            if (posY != this.getY()) return false;
            if (this.isFirstMove && Math.abs(posX - this.getX()) > 2) return false;
            if (!this.isFirstMove && Math.abs(posX - this.getX()) > 1) return false;
            if (Math.abs(posX - this.getX()) == 2) {
                if (board.get(posX+1).get(posY) != null) return false;
            }
        }

        return true;
    }
}
