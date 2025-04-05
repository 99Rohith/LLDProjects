
import java.util.ArrayList;

public abstract class Piece {
    private final boolean mWhite;
    private final char mLetter;
    private int mPosX;
    private int mPosY;
    private boolean mKilled;
    
    protected Piece(int posX, int posY, boolean isWhite, char letter) {
        this.mPosX = posX;
        this.mPosY = posY;
        this.mWhite = isWhite;
        this.mLetter = letter;
    }

    public boolean getWhite() {
        return this.mWhite;
    }

    public void setKilled() {
        this.mKilled = true;
    }

    public boolean getKilled() {
        return this.mKilled;
    }

    public void setX(int posX) {
        this.mPosX = posX;
    }

    public void setY(int posY) {
        this.mPosY = posY;
    }

    public int getX() {
        return this.mPosX;
    }

    public int getY() {
        return this.mPosY;
    }

    public char getLetter() {
        return this.mLetter;
    }

    protected boolean isValid(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }

    protected abstract boolean isValidMove(int posX, int posY, ArrayList<ArrayList<Piece>> board);
    public abstract boolean move(int posX, int posY, ArrayList<ArrayList<Piece>> board);
}
