package RideBooking;

public class Client {
    public static void main(String[] args) {
        DriverRepository driverRepository =
                new InMemoryDriverRepository();

        RideRepository rideRepository =
                new InMemoryRideRepository();

        Driver driver = new Driver(
                "D1",
                "Rahul",
                new Vehicle("KA01AB1234", "Swift"),
                new Location(12.9716, 77.5946)
        );

        driver.goOnline();

        driverRepository.save(driver);

        Rider rider = new Rider(
                "R1",
                "Zia"
        );

        PricingStrategy pricingStrategy =
                new NormalPricingStrategy();

        RideService rideService =
                new RideService(
                        driverRepository,
                        rideRepository,
                        pricingStrategy
                );

        Ride ride = rideService.requestRide(
                rider,
                new Location(12.9717, 77.5947),
                new Location(12.9816, 77.6046)
        );

        System.out.println(
                "Ride ID: " + ride.getId()
        );

        System.out.println(
                "Driver: " +
                ride.getDriver().getId()
        );

        System.out.println(
                "Fare: " +
                ride.getFare()
        );

        rideService.startRide(ride.getId());

        System.out.println(
                "State: " + ride.getState()
        );

        rideService.completeRide(ride.getId());

        System.out.println(
                "State: " + ride.getState()
        );
    }
}
