package IRCTC;

import java.util.List;
import java.util.ArrayList;

// Coach 1 [1,..,100] Coach 2 [Seat 101,...200]
public class Coach {
    private final String coachNumber;
     final List<Seat> seats;

    Coach(String coachNumber, List<Seat> seats) {
        this.coachNumber = coachNumber;
        this.seats = new ArrayList<>(seats);
    }

    // getters and setters to access private memebers
}
