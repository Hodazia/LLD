package DeliveryApp;

import java.util.List;

enum OrderType {
    DELIVERY,
    PICKUP
}
enum OrderStatus {

    CREATED,
    PAYMENT_PENDING,
    CONFIRMED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED,
    PICKED_UP,
    CANCELLED
}

// when we put items in the cart, it is not sure we will buy them, and after some time
// the price may change too!!
class OrderItem {

    private final Item item;
    private final int quantity;
    private final int priceAtPurchase;

    public OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.priceAtPurchase = item.getPrice();
    }

    public int getTotalPrice() {
        return priceAtPurchase * quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}

public class Order {
    private final int orderId;

    private final User user;
    private final Restaurant restaurant;
    private final List<OrderItem> items;
    private final OrderType orderType;
    private final Location deliveryLocation;
    private OrderStatus status;
    private DeliveryAgent deliveryAgent;
    private int totalAmount;

    public Order(int orderId,User user,Restaurant restaurant,List<OrderItem> items,OrderType orderType, Location deliveryLocation) {

        this.orderId = orderId;
        this.user = user;
        this.restaurant = restaurant;
        this.items = items;
        this.orderType = orderType;
        this.deliveryLocation = deliveryLocation;

        this.status = OrderStatus.CREATED;

        calculateTotal();
    }

    private void calculateTotal()
    {
        totalAmount = 0;
        for(OrderItem item:items)
        {
            totalAmount = totalAmount + item.getTotalPrice();
        }
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void assignDeliveryAgent(DeliveryAgent agent) {
        this.deliveryAgent = agent;
    }

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<OrderItem> getItems() {
        return items;
    }
    public OrderType getOrderType() {
        return orderType;
    }

    public Location getDeliveryLocation() {
        return deliveryLocation;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    public int getTotalAmount() {
        return totalAmount;
    }


}

