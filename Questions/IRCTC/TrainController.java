package IRCTC;

import java.util.List;
import java.time.LocalDate;
import IRCTC.enums.TicketType;

public class TrainController {
    private final JourneyRepository journeyRepository;
    private final BookingService bookingService;

    TrainController(JourneyRepository journeyRepository,
                    BookingService bookingService) {
        this.journeyRepository = journeyRepository;
        this.bookingService = bookingService;
    }

    // Customer searches for journeys.
    public List<TrainJourney> searchTrains(
            Customer customer,
            String source,
            String destination,
            LocalDate date) {

        return journeyRepository.search(
                source, destination, date);
    }

    // Customer books a journey.
    public Booking bookTicket(
            Customer customer,
            String journeyId,
            List<Passenger> passengers,
            TicketType ticketType,
            PaymentStrategy paymentStrategy) {

        return bookingService.book(
                customer,
                journeyId,
                passengers,
                ticketType,
                paymentStrategy
        );
    }

    // Customer checks booking.
    public Booking getBooking(
            Customer customer,
            String bookingId,
            BookingRepository repository) {

        Booking booking = repository.findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        if (!booking.customerId.equals(customer.getId())) {
            throw new SecurityException("Access denied");
        }

        return booking;
    }
}
