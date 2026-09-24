package DeliveryApp;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Restaurant restaurant1 = new Restaurant(1,"Mac D", new Location(10,10));
        Item burger = new Item(1,50,"Cheese Burger" );
        Item Fries = new Item(2,75,"Peri peri fries");

        restaurant1.add_item(Fries);
        restaurant1.add_item(burger);

        Restaurant restaurant2 = new Restaurant(2,"Dominos", new Location(30,15));
        Item Margerita = new Item(1,85,"margerita" );
        Item VegLoad = new Item(2,105,"veg loaded");

        restaurant2.add_item(Margerita);
        restaurant2.add_item(VegLoad);

        RestaurantController controller = new RestaurantController();
        controller.addRestaurant(restaurant1);
        controller.addRestaurant(restaurant2);

        // now baout the  user, profile of user
        Cart cart = new Cart(1);
        User user = new User(1,cart,"ZIAUL","WHITEFIELD", new Location(15,15));

        // find the restaurants earby user location within a radius of 10
        List<Restaurant> restaurants = controller.searchbylocation(user.getLocation(),10 );
        for (Restaurant restaurant : restaurants) {
            System.out.println("Restaurant found: " + restaurant.getName());
        }

        // now user add items from same restaurants ,
        user.addItem(restaurant2, VegLoad);
        user.addItem(restaurant2, Margerita);
        user.addItem(restaurant2, VegLoad);

        // create some deliveryagents
        List<DeliveryAgent> agents = new ArrayList<>();
        for(int i=1;i<5;i++)
        {
            agents.add(new DeliveryAgent(i, new Location(i*10,i*10)));
        }

        PaymentService paymentService = new PaymentService();

        DeliveryAssignmentService deliveryAssignmentService =new DeliveryAssignmentService(
                        agents);
        OrderSubject orderSubject =new OrderSubject();
        // Observer
        orderSubject.addObserver( new NotificationObserver());

        OrderService service = new OrderService(paymentService, deliveryAssignmentService, orderSubject);
        Order order = service.placeOrder(1001, user,OrderType.DELIVERY,new UPIPayment() );

        System.out.println(
            "Order ID: "+ order.getOrderId()
        );

        System.out.println("Amount: ₹"+ order.getTotalAmount());

        System.out.println("Status of the order now: "+ order.getStatus());
        System.out.println("Delivery Agent who has accepted the order: "+ order.getDeliveryAgent().getId()
        );
    }
}
