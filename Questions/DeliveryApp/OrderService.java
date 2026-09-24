package DeliveryApp;

import java.util.List;
import java.util.ArrayList;

public class OrderService {
    private final PaymentService paymentService;
    private final DeliveryAssignmentService deliveryAssignmentService;
    private final OrderSubject orderSubject;

    public OrderService(
            PaymentService paymentService,
            DeliveryAssignmentService deliveryAssignmentService,
            OrderSubject orderSubject) {

        this.paymentService = paymentService;
        this.deliveryAssignmentService = deliveryAssignmentService;
        this.orderSubject = orderSubject;
    }

    public Order placeOrder(int orderId, User user, OrderType orderType, PaymentStrategy paymentStrategy) 
    {
        Cart cart = user.getCart();

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException(
                    "Cart is empty"
            );
        }

        Restaurant restaurant = cart.getRestaurant();
        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem item:cart.getItems())
        {
            // get all the cart items from the cart and add it in the orderitems
            orderItems.add(new OrderItem(item.getItem(), item.getQuantity()));
        }

        Location deliveryLocation = user.getLocation();// get the user location
        Order order = new Order(orderId,user,restaurant,orderItems,orderType,deliveryLocation);
        // the above is the order which the user has placed, and will pay

        order.setStatus(OrderStatus.PAYMENT_PENDING);
        orderSubject.notifyObservers(order);

        boolean paymentSuccesful = paymentService.processPayment(order.getTotalAmount(),paymentStrategy);
        if(!paymentSuccesful)
        {
            // fail, then tell it to the user
            order.setStatus(OrderStatus.CANCELLED);
            orderSubject.notifyObservers(order);
            throw new IllegalStateException("Payment failed");
        }

        order.setStatus(OrderStatus.CREATED); // order is created 
        orderSubject.notifyObservers(order);

        if (orderType == OrderType.DELIVERY) {
            // if it is home delivery not pickup delivery
            DeliveryAgent agent = deliveryAssignmentService.assignAgent(order);
            if (agent == null) {
                throw new IllegalStateException(
                        "Could not assign delivery agent"
                );
            }

            order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
            orderSubject.notifyObservers(order);
        }
        return order;
    }
}
