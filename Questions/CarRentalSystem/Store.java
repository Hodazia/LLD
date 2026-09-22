package CarRentalSystem;
import java.util.ArrayList;
import java.util.List;

public class Store {
    /*store belongs to a location and contains many vehicles */
    private final String id;
    private final String name;
    private final Location location;
    VehicleInventoryManagement inventoryManagement;
    private final List<Vehicle> vehicles = new ArrayList<>();

    public Store(String id, String name, Location location)
    {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public List<Vehicle> getVehicles(VehicleType vehicleType) {

        return inventoryManagement.getVehicles();
    }


    //addVehicles, update vehicles,get vehicles, delete vehicles, use inventory management to update those.

    public void setVehicles(List<Vehicle> vehicles) {
        inventoryManagement = new VehicleInventoryManagement(vehicles);
    }
    // add vehicle
    public void add_vehicle(Vehicle v)
    {
        vehicles.add(v);
    }

    // remove vehicle
    public void remove_vehicle(Vehicle v)
    {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
