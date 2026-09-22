package Questions.Elevator;

public class Floor {
    private final int floorNumber;
    private final ExternalButton upButton;
    private final ExternalButton downButton;

    public Floor(int floorNumber, ElevatorDispatcher dispatcher) {
        this.floorNumber = floorNumber;
        this.upButton = new ExternalButton(dispatcher, floorNumber, Direction.UP);
        this.downButton = new ExternalButton(dispatcher, floorNumber, Direction.DOWN);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void pressUpButton() {
        upButton.press();
    }

    public void pressDownButton() {
        downButton.press();
    }
}
