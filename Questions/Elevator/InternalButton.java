package Questions.Elevator;

public class InternalButton {
    private final int elevatorId;
    private final int destinationFloor;
    private final ElevatorDispatcher dispatcher;

    public InternalButton(int elevatorId, int destinationFloor, ElevatorDispatcher dispatcher) {
        this.elevatorId = elevatorId;
        this.destinationFloor = destinationFloor;
        this.dispatcher = dispatcher;
    }

    public void press() {
        System.out.println("[Internal] Elevator " + elevatorId + " pressed floor " + destinationFloor);
        dispatcher.submitInternalRequest(elevatorId, destinationFloor);
    }
}
