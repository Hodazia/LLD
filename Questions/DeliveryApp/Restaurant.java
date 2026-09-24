package DeliveryApp;
import java.util.ArrayList;
import java.util.List;


public class Restaurant {
    private final int restId;
    private final String name;
    private final Location location;
    private final List<Item> items; // Itemis dependent on Restaurant, Composition relationhip

    public Restaurant(int id, String name, Location location)
    {
        this.restId = id;
        this.location = location;
        this.name = name;
        items = new ArrayList<>();
    }

    void add_item(Item item)
    {
        items.add(item);
    }

    List<Item> get_all_iems()
    {
        return items;
    }

    // getters and setters,
    public int getRestaurantId() {
        return restId;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
