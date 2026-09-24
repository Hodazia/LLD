package DeliveryApp;

import java.util.List;
import java.util.ArrayList;

public interface OrderObserver {
    void update(Order order);   
}

class NotificationObserver implements OrderObserver {
    @Override 
    public void update(Order order)
    {
        System.out.println("Notification sent to User " + order.getUser().getUserId() + 
    ": Order" + order.getOrderId() + " is now " + order.getStatus());
    }
}

class OrderSubject {

    private final List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }
}

/*
user is gonna observe the status of the order,
order is the subject, 

*/