package Questions.Elevator;

import java.util.List;

public class ElevatorDispatcher {
    private final List<ElevatorController> controllers;
    private final ElevatorDispatchStrategy strategy;

    public ElevatorDispatcher(List<ElevatorController> controllers, ElevatorDispatchStrategy strategy) {
        this.controllers = controllers;
        this.strategy = strategy;
    }

    public void submitRequest(ElevatorRequest request) {
        ElevatorController selected = strategy.select(controllers, request);
        if (selected == null) {
            System.out.println("No elevator available for " + request);
            return;
        }
        System.out.println("Dispatcher assigned " + request + " -> Elevator " + selected.getElevator().getId());
        selected.submitRequest(request);
    }

    public void submitInternalRequest(int elevatorId, int destinationFloor) {
        ElevatorController controller = controllers.stream()
                .filter(c -> c.getElevator().getId() == elevatorId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Elevator not found: " + elevatorId));

        controller.submitRequest(ElevatorRequest.internal(destinationFloor));
    }
}
