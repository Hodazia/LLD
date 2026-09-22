package Questions.Elevator;

public class Elevator {
    private final int id;
    private final Display display;
    private final Door door;
    private int currentFloor;
    private Direction direction;
    private ElevatorStatus status;

    public Elevator(int id, int startingFloor) {
        this.id = id;
        this.currentFloor = startingFloor;
        this.display = new Display(startingFloor);
        this.door = new Door();
        this.direction = Direction.IDLE;
        this.status = ElevatorStatus.IDLE;
    }

    public int getId() {
        return id;
    }

    public Display getDisplay() {
        return display;
    }

    public Door getDoor() {
        return door;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        display.update(currentFloor, direction);
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }

    public void moveToFloor(int targetFloor) {
        if (targetFloor == currentFloor) {
            return;
        }

        setStatus(ElevatorStatus.MOVING);
        setDirection(targetFloor > currentFloor ? Direction.UP : Direction.DOWN);

        while (currentFloor != targetFloor) {
            currentFloor += (targetFloor > currentFloor) ? 1 : -1;
            display.update(currentFloor, direction);
            System.out.println("  Elevator " + id + " moving -> floor " + currentFloor);
            sleep(300);
        }
    }

    public void stopAtCurrentFloor() {
        setStatus(ElevatorStatus.IDLE);
        display.update(currentFloor, Direction.IDLE);
        setDirection(Direction.IDLE);
        door.open();
        sleep(500);
        door.close();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Elevator-" + id + "{floor=" + currentFloor + ", direction=" + direction + ", status=" + status + "}";
    }
}
