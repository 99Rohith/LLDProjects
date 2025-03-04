
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeCalculator {
    public static int difference(int x, int y) {
        return y-x;
    }

    public static long difference(LocalDateTime start, LocalDateTime end) {
        // return ChronoUnit.HOURS.between(end, start);
        // for testing use seconds
        return ChronoUnit.SECONDS.between(end, start);
    }
}
