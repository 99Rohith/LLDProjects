
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BookingSystem {
    private final Map<Movie, Set<Show>> mShows = new HashMap<>();
    private final Map<User, ArrayList<BookingInfo>> mBookingDeatils = new HashMap<>();

    public void addShow(Show show) {
        if(!this.mShows.containsKey(show.getMovie())) {
            this.mShows.put(show.getMovie(), new HashSet<>());
        }

        this.mShows.get(show.getMovie()).add(show);
    }

    public BookingInfo bookTicket(User user, Show show, Screen.Seat seat) {
        BookingInfo bookingInfo = new BookingInfo(user, show, seat);
        if(!this.mBookingDeatils.containsKey(user)) {
            this.mBookingDeatils.put(user, new ArrayList<>());
        }

        if (!show.bookSeat(seat)) {
            System.err.println("Seat is already booked/Seat not found, try different seat");
            return null;
        }

        this.mBookingDeatils.get(user).add(bookingInfo);
        System.out.println("Successfully booked: " + bookingInfo.toString());
        return bookingInfo;
    }

    public void cancelBooking(BookingInfo bookingInfo) {
        if (bookingInfo == null) return;
        if(!this.mBookingDeatils.containsKey(bookingInfo.getUser())) {
            return;
        }

        this.mBookingDeatils.get(bookingInfo.getUser()).remove(bookingInfo);
        bookingInfo.getShow().cancelSeat(bookingInfo.getSeat());
        System.out.println("Successfully cancelled: " + bookingInfo.toString());
    }

    public Set<Show> getShows(Movie movie) {
        if (this.mShows.containsKey(movie)) return this.mShows.get(movie);
        return null;
    }

    public ArrayList<BookingInfo> getBookings(User user) {
        if (!mBookingDeatils.containsKey(user)) return null;
        return mBookingDeatils.get(user);
    }
}
