package CarRentalSystem;

public class Bike extends Vehicle {
    public Bike(
        String id,
        double pricePerHour,
        String storeId) {

    super(
        id,
        VehicleType.BIKE,
        2,
        pricePerHour,
        storeId
    );
}
}
