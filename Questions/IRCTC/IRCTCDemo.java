package IRCTC;

import java.util.List;
import java.util.ArrayList;
import IRCTC.enums.SeatType;
import IRCTC.enums.BirthType;
import java.time.LocalDate;
import IRCTC.enums.*;

/*

the entire workflow,

IRCTCDemo.main()
       |
       v
TrainController.searchTrains()
       |
       v
JourneyRepository.search()
       |
       v
TrainController.bookTicket()
       |
       v
BookingService.book()
       |
       +---- SeatInventory.holdSeats()
       |
       +---- TicketFactory.createTicket()
       |
       +---- PaymentService.pay()
       |             |
       |             +---- UpiPayment.pay()
       |
       +---- SeatInventory.confirmSeats()
       |
       +---- BookingRepository.save()
       |
       v
Return Booking to client

*/

public class IRCTCDemo {
    public static void main(String[] args) {
        Admin admin = new Admin(
            "A1", "IRCTC Admin", "admin@irctc.com");

        Customer customer = new Customer(
            "C1", "Zia", "zia@example.com");

         List<Seat> sleeperSeats = new ArrayList<>();
         sleeperSeats.add(new Seat(
                    "S1", SeatType.SLEEPER, BirthType.LOWER));
    
            sleeperSeats.add(new Seat(
                    "S2", SeatType.SLEEPER, BirthType.UPPER
            ));
    
            sleeperSeats.add(new Seat(
                    "S3", SeatType.SLEEPER, BirthType.LOWER
            ));
    
            sleeperSeats.add(new Seat(
                    "S4", SeatType.SLEEPER, BirthType.MIDDLE
            ));
    
        Coach coach = new Coach("S1", sleeperSeats);
    
        Train train = new Train(
                    "12301",
                    "Rajdhani Express",
                    List.of(coach));

        TrainRepository trainRepository = new InMemoryTrainRepository();
        JourneyRepository journeyRepository = new InMemoryJourneyRepository();
        BookingRepository bookingRepository = new InMemoryBookingRepository();
        AdminService adminService =new AdminService(
                        trainRepository,
                        journeyRepository
                );

        adminService.addTrain(admin, train);
        System.out.println("Train added successfully");

        LocalDate journeyDate =LocalDate.of(2026, 9, 26);

        TrainJourney journey = new TrainJourney(
                "JOURNEY_001",
                train,
                journeyDate,
                "New Delhi",
                "Mumbai",
                journeyDate.atTime(16, 0),
                journeyDate.plusDays(1).atTime(8, 0)
        );

        journeyRepository.save(journey);
        System.out.println("Journey created successfully");

        PaymentService paymentService = new PaymentService();
        TicketFactory ticketFactory = new TicketFactory();
        BookingService bookingService =new BookingService(
                        journeyRepository,
                        bookingRepository,
                        paymentService,
                        ticketFactory
                );

        TrainController controller =new TrainController(
                        journeyRepository,
                        bookingService
                );

        System.out.println("\nSearching trains...");
        List<TrainJourney> results =
                        controller.searchTrains(
                                customer,
                                "New Delhi",
                                "Mumbai",
                                journeyDate
                        );
        
        for (TrainJourney result : results) {
                    System.out.println(
                            result.train.trainNumber + " | "
                            + result.train.name + " | "
                            + result.source + " -> "
                            + result.destination + " | "
                            + result.journeyDate + " | "
                            + "Available seats: "
                            + result.inventory.availableSeats()
                    );
        }
        List<Passenger> passengers = List.of(
            new Passenger(
                    "Zia", 24, BirthType.LOWER
            ),
            new Passenger(
                    "Rahul", 25, BirthType.UPPER
            )
        );

        PaymentStrategy paymentStrategy = new UpiPayment();

        // =====================================
        // 10. BOOK THE TICKET
        // =====================================

        System.out.println("\nBooking ticket...");

        Booking booking = controller.bookTicket(
                customer,
                "JOURNEY_001",
                passengers,
                TicketType.CONFIRMED,
                paymentStrategy
        );

        System.out.println("\n--- BOOKING CONFIRMED ---");

        System.out.println("Booking ID: " + booking.bookingId);
        System.out.println("Ticket ID: " + booking.ticket.getTicketId());
        System.out.println("Customer: " + customer.getId());

        System.out.println("Booking Status: " + booking.status);
        System.out.println("Ticket Type: " + booking.ticket.getTicketType());
        System.out.println("Total Fare: Rs. " + booking.ticket.getFare());
        System.out.println("Allocated Seats:");

        for (Seat seat : booking.allocatedSeats) {
            System.out.println(
                    seat.seatNumber + " | "
                    + seat.seatType + " | "
                    + seat.berthType
            );
        }

        System.out.println(
                "Remaining seats: "
                + journey.inventory.availableSeats()
        );
    }
}
