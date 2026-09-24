package DeliveryApp;

public class DeliveryAgent {
    private final int id;
    private Location location;
    private Order order;
    //boolean accept;

    public DeliveryAgent(int id, Location location)
    {
        this.id = id;
        this.location = location;
        this.order = null;
    }

    public boolean isAvailable() {
        return order == null;
    }

    public boolean acceptOrder(Order order) {

        if (!isAvailable()) {
            return false;
        }

        this.order = order;
        return true;
    }

    public void completeOrder() {
        this.order = null;
    }

    public double distanceFrom(Location location) {
        return this.location.distanceTo(location);
    }

    public void updateLocation(Location location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public Order getOrder() {
        return order;
    }

}
