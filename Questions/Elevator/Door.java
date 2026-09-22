package Questions.Elevator;

public class Door {
    private DoorState state;

    public Door() {
        this.state = DoorState.CLOSED;
    }

    public DoorState getState() {
        return state;
    }

    public void open() {
        state = DoorState.OPEN;
        System.out.println("    [Door] Opened");
    }

    public void close() {
        state = DoorState.CLOSED;
        System.out.println("    [Door] Closed");
    }
}
