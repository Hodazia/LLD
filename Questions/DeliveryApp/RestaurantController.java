package DeliveryApp;
import java.util.ArrayList;
import java.util.List;


public class RestaurantController {
    // they are a singleton class, there shall be only 1 instance,
    private final List<Restaurant> restaurants;
    String location;

    //perform CRUD operations. search based on location
    public RestaurantController()
    {
        restaurants = new ArrayList<>();
    }

    public void addRestaurant(Restaurant restaurant)
    {
        restaurants.add(restaurant);
    }

    void delete_restaurant()
    {

    }

    List<Restaurant> searchbylocation(Location userLocation, double radius)
    {
        // search restaurant by user location ,within radius radius
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for(Restaurant restaurant: restaurants)
        {
            double distance = restaurant.getLocation().distanceTo(userLocation);
            if(distance<=radius)
            {
                filteredRestaurants.add(restaurant);
            }
        }
        return filteredRestaurants;
    }

}
