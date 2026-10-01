package IRCTC;

import IRCTC.enums.*;
import java.util.*;
import java.util.concurrent.*;

public class SeatLockingDemo {

    public static void main(String[] args)
            throws InterruptedException {

        // 1. Create only ONE available seat.
        Seat seat = new Seat(
                "S1",
                SeatType.SLEEPER,
                BirthType.LOWER
        );

        SeatInventory inventory =
                new SeatInventory(List.of(seat));

        // 2. Two different customers.
        Customer zia = new Customer(
                "C1", "Zia", "zia@example.com"
        );

        Customer rahul = new Customer(
                "C2", "Rahul", "rahul@example.com"
        );

        Passenger ziaPassenger = new Passenger(
                "Zia", 24, BirthType.LOWER
        );

        Passenger rahulPassenger = new Passenger(
                "Rahul", 25, BirthType.LOWER
        );

        // 3. Both threads wait until the same start signal.
        CountDownLatch startSignal =
                new CountDownLatch(1);

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        Runnable ziaBooking = () -> {
            try {
                startSignal.await();

                System.out.println(
                        zia.getId()
                        + " trying to book S1");

                List<Seat> allocated =
                        inventory.holdSeats(
                                List.of(ziaPassenger));

                inventory.confirmSeats(allocated);

                System.out.println(
                        zia.getId()
                        + " BOOKING SUCCESSFUL: "
                        + allocated.get(0).seatNumber);

            } catch (RuntimeException ex) {
                System.out.println(
                        zia.getId()
                        + " BOOKING FAILED: "
                        + ex.getMessage());

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable rahulBooking = () -> {
            try {
                startSignal.await();

                System.out.println(
                        rahul.getId()
                        + " trying to book S1");

                List<Seat> allocated =
                        inventory.holdSeats(
                                List.of(rahulPassenger));

                inventory.confirmSeats(allocated);

                System.out.println(
                        rahul.getId()
                        + " BOOKING SUCCESSFUL: "
                        + allocated.get(0).seatNumber);

            } catch (RuntimeException ex) {
                System.out.println(
                        rahul.getId()
                        + " BOOKING FAILED: "
                        + ex.getMessage());

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        };

        // 4. Submit both booking tasks.
        executor.submit(ziaBooking);
        executor.submit(rahulBooking);

        // 5. Release both threads at approximately the same time.
        startSignal.countDown();

        executor.shutdown();
        executor.awaitTermination(
                5, TimeUnit.SECONDS);

        System.out.println(
                "Final available seats: "
                + inventory.availableSeats());

        System.out.println(
                "Final seat status: " + seat.status);
    }
}