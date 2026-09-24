package DeliveryApp;

import java.util.List;

public class Order {
    int orderid;
    User user; // of which user
    List<Item> items;
    PaymentStrategy strategy;
    Restaurant restaurant;


}
