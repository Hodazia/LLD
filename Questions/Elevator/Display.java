package Questions.Elevator;

public class Display {
    private int currentFloor;
    private Direction direction;

    public Display(int initialFloor) {
        this.currentFloor = initialFloor;
        this.direction = Direction.IDLE;
    }

    public void update(int floor, Direction direction) {
        this.currentFloor = floor;
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Floor " + currentFloor + " | " + direction;
    }
}
