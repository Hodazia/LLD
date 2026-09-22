package CarRentalSystem;

public class Car extends Vehicle {
    public Car(
        String id,
        int seats,
        double pricePerHour,
        String storeId) {

    super(
        id,
        VehicleType.CAR,
        seats,
        pricePerHour,
        storeId
    );
}
}
