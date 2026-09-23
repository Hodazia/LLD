package BookMyShow;

import java.util.List;

public class Screen {
    // current show running, 
    int id;
    List<Seat> seats;
    public int getScreenId() {
        return id;
    }

    public void setScreenId(int screenId) {
        this.id = screenId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
