package RideBooking;

public class Vehicle{
    private final String vehicleNumber;
    private final String model;

    public Vehicle(String vehicleNumber, String model) {
        this.vehicleNumber = vehicleNumber;
        this.model = model;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getModel() {
        return model;
    }
}