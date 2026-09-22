package CarRentalSystem;

import java.time.LocalDateTime;

enum ReservationStatus {
    CONFIRMED,
    CANCELLED
}

public class Reservation {
    /*
    reservation id, of which vehicle, of which user,start and end time
    */
    private String id;
    private User user;
    private Vehicle vehicle;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private Location pickUpLocation;
    private Location dropLocation;

    private ReservationStatus status;

    public Reservation(
            String id,
            User user,
            Vehicle vehicle,
            LocalDateTime startTime,
            LocalDateTime endTime,
            Location pickupLocation, 
            Location dropLocation) {

        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pickUpLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.status = ReservationStatus.CONFIRMED;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void cancel() {
        status = ReservationStatus.CANCELLED;
    }

}
