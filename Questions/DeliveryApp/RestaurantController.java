package DeliveryApp;
import java.util.ArrayList;
import java.util.List;


public class RestaurantController {
    // they are a singleton class, there shall be only 1 instance,
    List<Restaurant> restaurants;
    String location;

    //perform CRUD operations. search based on location
    RestaurantController()
    {
        restaurants = new ArrayList<>();
    }

    void add_restaurant(Restaurant restaurant)
    {
        restaurants.add(restaurant);
    }

    void delete_restaurant()
    {

    }

    List<Restaurant> searchbylocation(String location)
    {
        // search restaurant by user location ,
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for(Restaurant restaurant: restaurants)
        {
            if(restaurant.location.equals(location))
            {
                filteredRestaurants.add(restaurant);
            }
        }
        return filteredRestaurants;
    }

}
