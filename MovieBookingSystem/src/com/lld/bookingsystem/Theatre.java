
import java.util.HashSet;
import java.util.Set;

public class Theatre {
    private String mTheatreName;
    private String mCity;
    private Set<Screen> mScreens = new HashSet<>();

    public Theatre(String theatreName, String city) {
        this.mTheatreName = theatreName;
        this.mCity = city;
    }

    public void addScreen(Screen screen) {
        this.mScreens.add(screen);
    }

    @Override
    public String toString() {
        return "Theatre: " + this.mTheatreName + ", City: " + this.mCity; 
    }
}
