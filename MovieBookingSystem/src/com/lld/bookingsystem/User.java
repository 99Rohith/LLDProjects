public class User {
    private int mUserId;

    public User(int userId) {
        this.mUserId = userId;
    }

    @Override
    public String toString() {
        return "User: " + String.valueOf(this.mUserId);
    }
}
