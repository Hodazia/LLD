package Questions.Elevator;

public class ExternalButton {
    private final ElevatorDispatcher dispatcher;
    private final int floorNumber;
    private final Direction direction;

    public ExternalButton(ElevatorDispatcher dispatcher, int floorNumber, Direction direction) {
        this.dispatcher = dispatcher;
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    public void press() {
        System.out.println("[External] Floor " + floorNumber + " pressed " + direction);
        dispatcher.submitRequest(ElevatorRequest.external(floorNumber, direction));
    }
}
