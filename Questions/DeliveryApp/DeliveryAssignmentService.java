package DeliveryApp;

import java.util.List;

public class DeliveryAssignmentService {
    private final List<DeliveryAgent> agents;

    public DeliveryAssignmentService(List<DeliveryAgent> agents) {
        this.agents = agents;
    }

    public DeliveryAgent assignAgent(Order order) {

        if (order.getOrderType() == OrderType.PICKUP) {
            return null;
        }

        // nearestagent calculation
        DeliveryAgent nearestAgent = null;
        double minDistance = Double.MAX_VALUE;
        Location restaurantLocation = order.getRestaurant().getLocation();

        for (DeliveryAgent agent : agents) {

            // if agent not available skip,
            if (!agent.isAvailable()) {
                continue;
            }
            double distance = agent.distanceFrom(restaurantLocation);

            if (distance < minDistance) {
                minDistance = distance;
                nearestAgent = agent;
            }
        }

        if (nearestAgent == null) {
                throw new IllegalStateException("No delivery agent available");
        }

        nearestAgent.acceptOrder(order);
        order.assignDeliveryAgent(nearestAgent);
        return nearestAgent;
    }
}
