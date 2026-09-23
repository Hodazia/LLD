package BookMyShow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Show {
    private int showId;
    private Movie movie;
    private Screen screen;
    private int startTime;
    private final List<Integer> bookedSeatIds = Collections.synchronizedList(new ArrayList<>());

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public int getShowStartTime() {
        return startTime;
    }

    public void setShowStartTime(int showStartTime) {
        this.startTime = showStartTime;
    }

    public List<Integer> getBookedSeatIds() {
        return bookedSeatIds;
    }

    public synchronized boolean bookSeat(int seatId) {
        if (bookedSeatIds.contains(seatId)) {
            return false;
        }
        bookedSeatIds.add(seatId);
        return true;
    }

    public Seat findSeat(int seatId) {
        for (Seat seat : screen.getSeats()) {
            if (seat.getSeatId() == seatId) {
                return seat;
            }
        }
        return null;
    }
}
