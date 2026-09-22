package Questions.Elevator;

public class ElevatorRequest {
    private final int floor;
    private final Integer destinationFloor;
    private final Direction direction;
    private final RequestType type;

    private ElevatorRequest(int floor, Integer destinationFloor, Direction direction, RequestType type) {
        this.floor = floor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
        this.type = type;
    }

    public static ElevatorRequest external(int floor, Direction direction) {
        return new ElevatorRequest(floor, null, direction, RequestType.EXTERNAL);
    }

    public static ElevatorRequest internal(int destinationFloor) {
        return new ElevatorRequest(destinationFloor, destinationFloor, Direction.IDLE, RequestType.INTERNAL);
    }

    public int getFloor() {
        return floor;
    }

    public Integer getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestType getType() {
        return type;
    }

    @Override
    public String toString() {
        if (type == RequestType.EXTERNAL) {
            return "ExternalRequest{floor=" + floor + ", direction=" + direction + "}";
        }
        return "InternalRequest{destination=" + destinationFloor + "}";
    }
}
