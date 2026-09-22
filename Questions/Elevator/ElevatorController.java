package Questions.Elevator;

import java.util.TreeSet;

/**
 * Controls a single elevator using the LOOK algorithm.
 * upStops  -> floors to visit while moving up   (ascending)
 * downStops -> floors to visit while moving down (descending)
 */
public class ElevatorController implements Runnable {
    private final Elevator elevator;
    private final TreeSet<Integer> upStops = new TreeSet<>();
    private final TreeSet<Integer> downStops = new TreeSet<>();

    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public synchronized void submitRequest(ElevatorRequest request) {
        System.out.println("Elevator " + elevator.getId() + " received " + request);

        if (request.getType() == RequestType.EXTERNAL) {
            if (request.getDirection() == Direction.UP) {
                upStops.add(request.getFloor());
            } else {
                downStops.add(request.getFloor());
            }
        } else {
            routeInternalRequest(request.getDestinationFloor());
        }

        notifyAll();
    }

    private void routeInternalRequest(int destinationFloor) {
        int current = elevator.getCurrentFloor();
        if (destinationFloor > current) {
            upStops.add(destinationFloor);
        } else if (destinationFloor < current) {
            downStops.add(destinationFloor);
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Integer nextStop;
            synchronized (this) {
                nextStop = pollNextStop();
                while (nextStop == null) {
                    elevator.setStatus(ElevatorStatus.IDLE);
                    elevator.setDirection(Direction.IDLE);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    nextStop = pollNextStop();
                }
            }
            elevator.moveToFloor(nextStop);
            elevator.stopAtCurrentFloor();
        }
    }

    private Integer pollNextStop() {
        int current = elevator.getCurrentFloor();
        Direction direction = elevator.getDirection();

        if (direction == Direction.IDLE) {
            direction = chooseDirectionWhenIdle(current);
            if (direction == Direction.IDLE) {
                return null;
            }
            elevator.setDirection(direction);
        }

        if (direction == Direction.UP) {
            Integer stop = upStops.ceiling(current);
            if (stop != null) {
                upStops.remove(stop);
                return stop;
            }
            downStops.remove(current);
            elevator.setDirection(Direction.DOWN);
            return pollNextStop();
        }

        Integer stop = downStops.floor(current);
        if (stop != null) {
            downStops.remove(stop);
            return stop;
        }
        upStops.remove(current);
        elevator.setDirection(Direction.UP);
        return pollNextStop();
    }

    private Direction chooseDirectionWhenIdle(int current) {
        Integer nextUp = upStops.ceiling(current);
        Integer nextDown = downStops.floor(current);

        if (nextUp == null && nextDown == null) {
            return Direction.IDLE;
        }
        if (nextUp == null) {
            return Direction.DOWN;
        }
        if (nextDown == null) {
            return Direction.UP;
        }

        int distUp = nextUp - current;
        int distDown = current - nextDown;
        return distUp <= distDown ? Direction.UP : Direction.DOWN;
    }
}
