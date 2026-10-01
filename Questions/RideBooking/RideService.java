package RideBooking;

import java.util.List;
import java.util.ArrayList;;

public class RideService {
    // private final DriverRepository driverRepository;

    // is there a need for AtomicLong,
    private final DriverRepository driverRepository;
    private final RideRepository rideRepository;
    private final PricingStrategy pricingStrategy;

    private final AtomicLong rideIdGenerator =
            new AtomicLong(1);

    public RideService(
            DriverRepository driverRepository,
            RideRepository rideRepository,
            PricingStrategy pricingStrategy
    ) {
        this.driverRepository = driverRepository;
        this.rideRepository = rideRepository;
        this.pricingStrategy = pricingStrategy;
    }

    public Ride requestRide(Rider rider,Location source,Location destination)
    {
        Ride ride = new Ride(
            "RIDE-" + rideIdGenerator.getAndIncrement(),
            rider,
            source,
            destination
        );

        double fare = pricingStrategy.calculateFare(source,destination);
        ride.setFare(fare);

        List<Driver> drivers = driverRepository.findNearbyAvailableDrivers(source);
        for (Driver driver : drivers) {

            /*
             * Multiple ride requests may have received
             * the same driver in their candidate list.
             *
             * tryAssignRide() is thread-safe.
             */
            if (driver.tryAssignRide(ride)) {

                ride.assignDriver(driver);
                rideRepository.save(ride);
                return ride;
            }
            throw new IllegalStateException(
                "No driver available"
        );
        }
    }

    public void startRide(String rideId)
    {
        Ride ride = getRide(rideId);
        ride.start();
    }

    public void completeRide(String rideId) {

        Ride ride = getRide(rideId);
        ride.complete();
        Driver driver = ride.getDriver();

        if (driver != null) {
            driver.releaseRide();
        }
    }

    public void cancelRide(String rideId) {

        Ride ride = getRide(rideId);
        ride.cancel();
        Driver driver = ride.getDriver();

        if (driver != null) {
            driver.releaseRide();
        }
    }

    private Ride getRide(String rideId) {

        return rideRepository
                .findById(rideId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Ride not found"
                        ));
    }

}
