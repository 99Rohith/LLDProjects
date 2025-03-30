public class BookingInfo {
    private final User mUser;
    private final Show mShow;
    private final Screen.Seat mSeat;

    public BookingInfo(User user, Show show, Screen.Seat seat) {
        this.mUser = user;
        this.mShow = show;
        this.mSeat = seat;
    }

    public User getUser() {
        return this.mUser;
    }

    public Show getShow() {
        return mShow;
    }

    public Screen.Seat getSeat() {
        return mSeat;
    }

    @Override
    public String toString() {
        return this.mUser.toString() + ", " + this.mShow.toString() + ", " + this.mSeat.toString();
    }
}
