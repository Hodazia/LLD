package IRCTC;

import java.util.List;
import java.util.ArrayList;
import IRCTC.enums.BookingStatus;

public class Booking {
    final String bookingId;
    final String customerId;
    final String journeyId;
    final List<Passenger> passengers;
    final List<Seat> allocatedSeats;
    final Ticket ticket;

    volatile BookingStatus status;

    Booking(String bookingId, String customerId,
            String journeyId,
            List<Passenger> passengers,
            List<Seat> seats, Ticket ticket) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.journeyId = journeyId;
        this.passengers = new ArrayList<>(passengers);
        this.allocatedSeats = new ArrayList<>(seats);
        this.ticket = ticket;
        this.status = BookingStatus.PENDING;
    }
}
