package CarRentalSystem;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDateTime;


public class RentalService {
    private final Map<String, Location>locations = new HashMap<>();
    private final Map<String, Store>stores = new HashMap<>();
    private final Map<String, Reservation> reservations = new HashMap<>();

    private final Map<String, Bill> bills = new HashMap<>();
    public void addLocation(Location location) {
            locations.put(location.getaddress(),location);
        }


    public void addStore(Store store) {
            stores.put(store.getId(),store);
        }

    public List<Vehicle> searchVehicles(
                String locationId,
                VehicleType type,
                Integer minSeats,
                LocalDateTime start,
                LocalDateTime end) {

            List<Vehicle> result =
                    new ArrayList<>();

            for (Store store : stores.values()) {

                if (!store.getLocation().getId().equals(locationId)) {
                    continue;
                }

                for (Vehicle vehicle : store.getVehicles()) {
                    // Vehicle type filter
                    if (type != null &&vehicle.getType() != type) {
                        continue;
                    }
                    // Seats filter
                    if (minSeats != null && vehicle.getSeats() < minSeats) {
                        continue;
                    }

                    // Availability filter
                    if (!isAvailable(vehicle, start, end)) {
                        continue;
                    }

                    result.add(vehicle);
                }
            }

            return result;
        }

        private boolean isAvailable(Vehicle vehicle,LocalDateTime requestedStart,LocalDateTime requestedEnd) {
            for (Reservation reservation :
                    reservations.values()) {

                if (reservation.getStatus() == ReservationStatus.CANCELLED) {
                    continue;
                }

                if (!reservation.getVehicle().getId().equals(vehicle.getId())) {
                    continue;
                }

                boolean overlaps = reservation.getStartTime().isBefore(requestedEnd)
                        &&
                        requestedStart.isBefore(reservation.getEndTime());

                if (overlaps) {
                    return false;
                }
            }

            return true;
        }

        public synchronized Reservation reserveVehicle(
                String reservationId,
                User user,
                Vehicle vehicle,
                LocalDateTime start,
                LocalDateTime end,
                Location pickup,
                Location drop
            ) {

            // Check again because
            // search result may be stale.
            if (!isAvailable(
                    vehicle,
                    start,
                    end)) {

                throw new IllegalStateException(
                    "Vehicle is no longer available"
                );
            }

            Reservation reservation = new Reservation(
                        reservationId,
                        user,
                        vehicle,
                        start,
                        end,
                        pickup,
                        drop
                    );

            reservations.put(reservationId,reservation);
            return reservation;
        }

        public Bill generateBill(String billId, Reservation reservation) {

            long hours =
                Duration.between(
                    reservation.getStartTime(),
                    reservation.getEndTime()
                ).toHours();

            if (hours <= 0) {
                throw new IllegalArgumentException(
                    "Invalid rental duration"
                );
            }

            double amount = hours *  reservation.getVehicle().getPricePerHour();

            Bill bill = new Bill(
                        billId,
                        reservation,
                        amount
                    );

            bills.put(
                billId,
                bill
            );

            return bill;
        }
        public void payBill(
                String billId,
                PaymentType paymentType) {

            Bill bill = bills.get(billId);

            if (bill == null) {
                throw new IllegalArgumentException(
                    "Bill not found"
                );
            }

            if (bill.getStatus()
                    == BillStatus.PAID) {

                throw new IllegalStateException(
                    "Bill already paid"
                );
            }

            Payment payment = PaymentFactory.createPayment(
                            paymentType
                        );

            payment.pay(bill);
            bill.markPaid();
        }
}
