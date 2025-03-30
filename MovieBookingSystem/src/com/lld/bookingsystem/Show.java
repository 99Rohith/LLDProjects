
import java.time.LocalDateTime;
import java.util.Set;

public class Show {
    private final Movie mMovie;
    private final Theatre mThretre;
    private final Screen mScreen;
    private final LocalDateTime mShowTime;
    private final Set<Screen.Seat> mAvailableSeats;

    public Show(Movie movie, Theatre theatre, Screen screen, LocalDateTime showTime, boolean seats) {
        this.mMovie = movie;
        this.mThretre = theatre;
        this.mScreen = screen;
        this.mShowTime = showTime;
        this.mAvailableSeats = screen.getAllSeats();
    }

    public Show(Movie movie, Theatre theatre, Screen screen, LocalDateTime showTime, Set<Screen.Seat> seats) {
        this.mMovie = movie;
        this.mThretre = theatre;
        this.mScreen = screen;
        this.mShowTime = showTime;
        this.mAvailableSeats = seats;
    }

    public boolean bookSeat(Screen.Seat seat) {
        if (!this.mAvailableSeats.contains(seat)) return false;
        this.mAvailableSeats.remove(seat);
        return true;
    }

    public void cancelSeat(Screen.Seat seat) {
        this.mAvailableSeats.add(seat);
    }

    public Movie getMovie() {
        return this.mMovie;
    }

    @Override
    public String toString() {
        return this.mThretre.toString() + ", " + this.mMovie.toString() + ", " +
                this.mScreen.toString() + ", Time: " + String.valueOf(this.mShowTime);
    }
}
