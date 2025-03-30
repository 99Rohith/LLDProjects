
import java.util.HashSet;
import java.util.Set;

public class Screen {
    public static class Seat {
        private int mRow;
        private int mCol;

        public Seat(int row, int col) {
            this.mRow = row;
            this.mCol = col;
        }

        @Override
        public String toString() {
            return "Row: " + String.valueOf(this.mRow) + ", Col: " + String.valueOf(this.mCol);
        }
    }

    private final int mScreenId;
    private final Set<Screen.Seat> mAvailableSeats = new HashSet<>();

    public Screen(int screenId) {
        this.mScreenId = screenId;
    }

    public void addSeat(Seat seat) {
        mAvailableSeats.add(seat);
    }

    public Set<Screen.Seat> getAllSeats() {
        return this.mAvailableSeats;
    }

    @Override
    public String toString() {
        return "Screen: " + this.mScreenId;
    }
}
