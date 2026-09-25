package IRCTC;

import IRCTC.enums.SeatType;
import IRCTC.enums.SeatStatus;
import IRCTC.enums.BirthType;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// 
public class Seat {
    SeatType seatType;
    final String seatNumber;
    final BirthType berthType;

    // Guarded by the journey's inventory lock.
    SeatStatus status = SeatStatus.AVAILABLE;

    Seat(String seatNumber, SeatType seatType, BirthType berthType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.berthType = berthType;
    }
}

/*


AVAILABLE  --->  HELD  --->  BOOKED
                 |
                 +-------> AVAILABLE (if booking fails)

*/


// why need of seat inventory, so
/*
so for diffrent journey the seats can be changed,

locks are used to prevent 2 different threads to do booking,
or holding seats as well as 




*/
class SeatInventory{
    // why is there a need for this
    private final Map<String, Seat> seats = new HashMap<>();

    // One lock per journey, not one global application lock.
    private final Object lock = new Object();

    SeatInventory(List<Seat> allSeats) {
        for (Seat seat : allSeats) {
            seats.put(seat.seatNumber, seat);
        }
    }

    List<Seat> holdSeats(List<Passenger> passengers) {
        synchronized (lock) {
            List<Seat> selected = new ArrayList<>();

            for (Passenger passenger : passengers) {
                Seat seat = findAvailableSeat(
                        passenger.preference, selected);

                if (seat == null) {
                    // Roll back the seats selected in this attempt.
                    for (Seat s : selected) {
                        s.status = SeatStatus.AVAILABLE;
                    }
                    throw new RuntimeException(
                            "Insufficient seats available");
                }

                seat.status = SeatStatus.HELD;
                selected.add(seat);
            }

            return selected;
        }
    }

    private Seat findAvailableSeat(BirthType preference, List<Seat> selected) {
        /*
        for a given list of seats check if the selected seats are available,

        does it returns all the selected seats, or it filter out from the selected seats
        */
        // First try to satisfy the berth preference.
        for (Seat seat : seats.values()) {
            if (seat.status == SeatStatus.AVAILABLE
                    && seat.berthType == preference
                    && !selected.contains(seat)) {
                return seat;
            }
        }

        // Fall back to any available berth.
        for (Seat seat : seats.values()) {
            if (seat.status == SeatStatus.AVAILABLE
                    && !selected.contains(seat)) {
                return seat;
            }
        }

        return null;
    }

    void confirmSeats(List<Seat> selected) {
        synchronized (lock) {
            for (Seat seat : selected) {
                if (seat.status != SeatStatus.HELD) {
                    throw new IllegalStateException(
                            "Seat is not held");
                }
            }

            for (Seat seat : selected) {
                seat.status = SeatStatus.BOOKED;
            }
        }
    }

    void releaseSeats(List<Seat> selected) {
        synchronized (lock) {
            for (Seat seat : selected) {
                if (seat.status == SeatStatus.HELD) {
                    seat.status = SeatStatus.AVAILABLE;
                }
            }
        }
    }
}