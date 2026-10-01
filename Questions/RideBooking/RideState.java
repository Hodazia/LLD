package RideBooking;

public interface RideState {
    void assignDriver(Ride ride,Driver driver);
    void startRide(Ride ride);
    void completeRide(Ride ride);
    void cancelRide(Ride ride);
    String getName();
}

class RequestedState implements RideState{
    @Override 
    public void assignDriver(Ride ride,Driver driver)
    {
        ride.setDriver(driver);
        ride.setState(new DriverAssignedState());
    }

    @Override
    public void startRide(Ride ride) {
        throw new IllegalStateException(
                "Cannot start ride before driver assignment"
        );
    }

    @Override
    public void completeRide(Ride ride) {
        throw new IllegalStateException(
                "Cannot complete requested ride"
        );
    }

    @Override
    public void cancelRide(Ride ride) {
        ride.setState(new CancelledState());
    }

    @Override
    public String getName() {
        return "REQUESTED";
    }
}


class DriverAssignedState implements RideState {
    @Override
    public void assignDriver(Ride ride, Driver driver) {
        throw new IllegalStateException(
                "Driver already assigned"
        );
    }

    @Override
    public void startRide(Ride ride) {
        ride.setState(new StartedState());
    }

    @Override
    public void completeRide(Ride ride) {
        throw new IllegalStateException(
                "Ride hasn't started"
        );
    }

    @Override
    public void cancelRide(Ride ride) {
        ride.setState(new CancelledState());
    }

    @Override
    public String getName() {
        return "DRIVER_ASSIGNED";
    }
}

class StartedState implements RideState {

    @Override
    public void assignDriver(Ride ride, Driver driver) {
        throw new IllegalStateException(
                "Cannot assign driver after ride started"
        );
    }

    @Override
    public void startRide(Ride ride) {
        throw new IllegalStateException("Ride already started");
    }

    @Override
    public void completeRide(Ride ride) {
        ride.setState(new CompletedState());
    }

    @Override
    public void cancelRide(Ride ride) {
        throw new IllegalStateException(
                "Cannot cancel an ongoing ride"
        );
    }

    @Override
    public String getName() {
        return "STARTED";
    }
}

class CompletedState implements RideState {

    @Override
    public void assignDriver(Ride ride, Driver driver) {
        throw new IllegalStateException("Ride completed");
    }

    @Override
    public void startRide(Ride ride) {
        throw new IllegalStateException("Ride completed");
    }

    @Override
    public void completeRide(Ride ride) {
        throw new IllegalStateException("Ride already completed");
    }

    @Override
    public void cancelRide(Ride ride) {
        throw new IllegalStateException("Ride completed");
    }

    @Override
    public String getName() {
        return "COMPLETED";
    }
}

class CancelledState implements RideState {

    @Override
    public void assignDriver(Ride ride, Driver driver) {
        throw new IllegalStateException("Ride cancelled");
    }

    @Override
    public void startRide(Ride ride) {
        throw new IllegalStateException("Ride cancelled");
    }

    @Override
    public void completeRide(Ride ride) {
        throw new IllegalStateException("Ride cancelled");
    }

    @Override
    public void cancelRide(Ride ride) {
        throw new IllegalStateException("Ride already cancelled");
    }

    @Override
    public String getName() {
        return "CANCELLED";
    }
}