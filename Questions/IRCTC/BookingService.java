package IRCTC;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import IRCTC.enums.*;


public class BookingService {
    private final JourneyRepository journeyRepository;
    private final BookingRepository bookingRepository;
    private final PaymentService paymentService;
    private final TicketFactory ticketFactory;

    BookingService(JourneyRepository journeyRepository,
                   BookingRepository bookingRepository,
                   PaymentService paymentService,
                   TicketFactory ticketFactory) {
        this.journeyRepository = journeyRepository;
        this.bookingRepository = bookingRepository;
        this.paymentService = paymentService;
        this.ticketFactory = ticketFactory;
    }

    public Booking book(
            Customer customer,
            String journeyId,
            List<Passenger> passengers,
            TicketType ticketType,
            PaymentStrategy paymentStrategy) {

        TrainJourney journey = journeyRepository
                .findById(journeyId)
                .orElseThrow(() ->
                        new RuntimeException("Journey not found"));

        if (journey.status != JourneyStatus.SCHEDULED) {
            throw new RuntimeException("Journey cancelled");
        }

        if (passengers == null || passengers.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one passenger required");
        }

        // Step 1: Atomically hold seats.
        List<Seat> seats = journey.inventory.holdSeats(passengers);
        String bookingId = UUID.randomUUID().toString();
        double baseFare = seats.size() * 1000.0;

        // Step 2: Factory creates the ticket.
        Ticket ticket = ticketFactory.createTicket(
                ticketType, baseFare);

        Booking booking = new Booking(
                bookingId,
                customer.getId(),
                journeyId,
                passengers,
                seats,
                ticket
        );

        bookingRepository.save(booking);

        try {
            // Step 3: Pay outside the inventory lock.
            boolean paid = paymentService.pay(
                    bookingId,
                    ticket.getFare(),
                    paymentStrategy
            );

            if (!paid) {
                journey.inventory.releaseSeats(seats);
                booking.status = BookingStatus.FAILED;
                bookingRepository.save(booking);
                throw new RuntimeException("Payment failed");
            }

            // Step 4: Finalize seats and booking.
            journey.inventory.confirmSeats(seats);
            booking.status = BookingStatus.CONFIRMED;
            bookingRepository.save(booking);

            return booking;

        } catch (RuntimeException ex) {
            journey.inventory.releaseSeats(seats);

            booking.status = BookingStatus.FAILED;
            bookingRepository.save(booking);

            throw ex;
        }
    }
}
