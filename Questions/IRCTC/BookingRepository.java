package IRCTC;

import java.util.Optional;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public interface BookingRepository {
    void save(Booking booking);
    Optional<Booking> findById(String bookingId);
}

class InMemoryBookingRepository implements BookingRepository {
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    public void save(Booking booking) {
        bookings.put(booking.bookingId, booking);
    }

    public Optional<Booking> findById(String id) {
        return Optional.ofNullable(bookings.get(id));
    }
}
