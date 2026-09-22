package CarRentalSystem;

import java.time.LocalDateTime;
import java.util.List;

public class main {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();

        Location downtown = new Location("LOC-1", "Bangalore");
        rentalService.addLocation(downtown);

        Store store = new Store("STORE-1", "Downtown Rentals", downtown);
        Car sedan = new Car("CAR-1", 4, 500.0, store.getId());
        Car suv = new Car("CAR-2", 7, 800.0, store.getId());
        Bike bike = new Bike("BIKE-1", 100.0, store.getId());
        store.add_vehicle(sedan);
        store.add_vehicle(suv);
        store.add_vehicle(bike);
        rentalService.addStore(store);

        User user = new User("USER-1", "Ziaul", "zia@example.com");

        LocalDateTime start = LocalDateTime.of(2026, 3, 10, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 10, 14, 0);

        System.out.println("--- Search: cars with 4+ seats at LOC-1 ---");
        List<Vehicle> results = rentalService.searchVehicles(
                downtown.getId(), VehicleType.CAR, 4, start, end);
        for (Vehicle vehicle : results) {
            System.out.println("  " + vehicle.getId() + " | seats=" + vehicle.getSeats()
                    + " | ₹" + vehicle.getPricePerHour() + "/hr");
        }

        System.out.println("\n--- Reserve CAR-1 ---");
        Reservation reservation = rentalService.reserveVehicle(
                "RES-1", user, sedan, start, end, downtown, downtown);
        System.out.println("  Reservation " + reservation.getId() + " confirmed");

        System.out.println("\n--- Overlapping reserve (should fail) ---");
        try {
            rentalService.reserveVehicle(
                    "RES-2", user, sedan,
                    LocalDateTime.of(2026, 3, 10, 13, 0),
                    LocalDateTime.of(2026, 3, 10, 16, 0),
                    downtown, downtown);
            System.out.println("  ERROR: overlap was allowed");
        } catch (IllegalStateException e) {
            System.out.println("  Rejected: " + e.getMessage());
        }

        System.out.println("\n--- Non-overlapping reserve (should succeed) ---");
        Reservation reservation2 = rentalService.reserveVehicle(
                "RES-3", user, sedan,
                LocalDateTime.of(2026, 3, 10, 14, 0),
                LocalDateTime.of(2026, 3, 10, 17, 0),
                downtown, downtown);
        System.out.println("  Reservation " + reservation2.getId() + " confirmed");

        System.out.println("\n--- Generate and pay bill ---");
        Bill bill = rentalService.generateBill("BILL-1", reservation);
        System.out.println("  Bill " + bill.getId() + " amount: ₹" + bill.getAmount());
        rentalService.payBill("BILL-1", PaymentType.UPI);
        System.out.println("  Bill status: " + bill.getStatus());
    }
}
