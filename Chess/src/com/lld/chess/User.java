public class User {
    private final boolean mPickedWhite;
    private final String mName;

    public User(boolean pickWhite, String name) {
        this.mPickedWhite = pickWhite;
        this.mName = name;
    }

    public boolean getPickedWhite() {
        return this.mPickedWhite;
    }

    public String getName() {
        return this.mName;
    }
}
