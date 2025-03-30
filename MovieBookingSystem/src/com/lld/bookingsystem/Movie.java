public class Movie {
    private String mMovieName;

    public Movie(String movieName) {
        this.mMovieName = movieName;
    }

    @Override
    public String toString() {
        return "Movie: " + this.mMovieName;
    }
}
