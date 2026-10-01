package RideBooking;

public class Ride {
    private final String id;
    private final Rider rider;
    private final Location source;
    private final Location destination;

    private Driver driver;

    private double fare;

    private RideState state;

    public Ride(String id,Rider rider,Location source,Location destination)
    {
        this.id = id;
        this.rider = rider;
        this.source = source;
        this.destination = destination;
        this.state = new RequestedState();
    }

    // getters and setters
    public synchronized void assignDriver(Driver driver) {
        state.assignDriver(this, driver);
    }

    public synchronized void start() {
        state.startRide(this);
    }

    public synchronized void complete()
    {
        state.completeRide(this);
    }

    public synchronized void cancel()
    {
        state.cancelRide(this);
    }

    public synchronized void setDriver(Driver driver)
    {
        this.driver = driver;
    }

    public synchronized void setState(RideState state)
    {
        this.state = state;
    }
    
    public synchronized String getState() {
        return state.getName();
    }

    public String getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public Driver getDriver() {
        return driver;
    }

    public synchronized double getFare() {
        return fare;
    }

    public synchronized void setFare(double fare) {
        this.fare = fare;
    }
}
