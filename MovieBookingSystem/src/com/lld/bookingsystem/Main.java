
import java.time.LocalDateTime;
import java.util.Set;

/**
 * % javac -d ./build/ ./src/com/lld/bookingsystem/*.java
 * % cd build
 * build % java Main
 */

/**
 * System has cinemas located in different cities.
 * Each cinema will have multiple screens, and users can book one or more seats for a
 * given movie show.
 * System should be able to add new cinemas and movie shows in those cinemas.
 * Users should be able to list all cinema's in their city which are displaying
 * a particular movie.
 * For a given cinema, users should also be able to list all shows which are
 * displaying a particular movie.
 */
public class Main {
    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();
        User user1 = new User(1);
        User user2 = new User(2);
        Theatre theatre1 = new Theatre("one", "BLR");
        Theatre theatre2 = new Theatre("two", "BLR");
        Screen screen1 = new Screen(1);
        Screen screen2 = new Screen(2);
        Screen screen3 = new Screen(3);
        Screen.Seat seat1 = new Screen.Seat(0, 0);
        Screen.Seat seat2 = new Screen.Seat(0, 1);
        Screen.Seat seat3 = new Screen.Seat(1, 0);
        Screen.Seat seat4 = new Screen.Seat(1, 1);
        Movie movie1 = new Movie("movie1");
        Movie movie2 = new Movie("movie2");
        Movie movie3 = new Movie("movie3");
        Show show1 = new Show(movie1, theatre1, screen1, LocalDateTime.now(), true);
        Show show2 = new Show(movie2, theatre1, screen2, LocalDateTime.now(), true);
        Show show3 = new Show(movie1, theatre2, screen1, LocalDateTime.now(), true);
        Show show4 = new Show(movie2, theatre2, screen2, LocalDateTime.now(), true);
        Show show5 = new Show(movie3, theatre2, screen3, LocalDateTime.now(), true);
        screen1.addSeat(seat1);
        screen1.addSeat(seat2);
        screen1.addSeat(seat3);
        screen1.addSeat(seat4);
        screen2.addSeat(seat1);
        screen2.addSeat(seat2);
        screen2.addSeat(seat3);
        screen3.addSeat(seat1);
        screen3.addSeat(seat2);
        screen3.addSeat(seat3);
        screen3.addSeat(seat4);
        theatre1.addScreen(screen1);
        theatre1.addScreen(screen2);
        theatre2.addScreen(screen1);
        theatre2.addScreen(screen2);
        theatre2.addScreen(screen3);
        bookingSystem.addShow(show1);
        bookingSystem.addShow(show2);
        bookingSystem.addShow(show3);
        bookingSystem.addShow(show4);
        bookingSystem.addShow(show5);

        Set<Show> availableShows = bookingSystem.getShows(movie3);
        if (availableShows != null) {
            for (Show show: availableShows) {
                System.out.println(show.toString());
            }
        } else {
            System.err.println("No shows available for movie: " + movie3.toString());
        }

        BookingInfo bookingInfo1 = bookingSystem.bookTicket(user2, show5, seat4);
        BookingInfo bookingInfo2 = bookingSystem.bookTicket(user2, show5, seat4);

        bookingSystem.cancelBooking(bookingInfo1);
        bookingSystem.cancelBooking(bookingInfo2);
    }
}
