package CarRentalSystem;

import java.util.List;

// Every store has a vehicle inventory management System.
public class VehicleInventoryManagement {
    List<Vehicle> vehicles;

    VehicleInventoryManagement(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    // we can get vehicles based on many filters,
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    //some  CRUD operations :  add , remove, get and delete vehicle.
}
