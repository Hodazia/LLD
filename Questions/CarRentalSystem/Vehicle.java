package CarRentalSystem;

enum VehicleType { 
    CAR,
    BIKE,
    TRUCK
}


public abstract class Vehicle {
    private final String id;
    private final VehicleType type;
    private final int seats;
    private final double priceperHour;
    private  final String storeId;

    protected Vehicle(
        String id,
        VehicleType type,
        int seats,
        double pricePerHour,
        String storeId) {

        this.id = id;
        this.type = type;
        this.seats = seats;
        this.priceperHour = pricePerHour;
        this.storeId = storeId;
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    public double getPricePerHour() {
        return priceperHour;
    }

    public String getStoreId() {
        return storeId;
    }
}
