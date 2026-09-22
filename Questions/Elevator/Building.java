package Questions.Elevator;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private final String name;
    private final int minFloor;
    private final int maxFloor;
    private final List<Floor> floors;
    private final List<ElevatorController> elevatorControllers;
    private final ElevatorDispatcher dispatcher;
    private final List<Thread> workerThreads = new ArrayList<>();

    public Building(String name, int minFloor, int maxFloor, int elevatorCount) {
        this.name = name;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.floors = new ArrayList<>();
        this.elevatorControllers = new ArrayList<>();

        for (int i = 0; i < elevatorCount; i++) {
            Elevator elevator = new Elevator(i + 1, minFloor);
            elevatorControllers.add(new ElevatorController(elevator));
        }

        this.dispatcher = new ElevatorDispatcher(elevatorControllers, new NearestElevatorStrategy());

        for (int floor = minFloor; floor <= maxFloor; floor++) {
            floors.add(new Floor(floor, dispatcher));
        }
    }

    public void start() {
        for (ElevatorController controller : elevatorControllers) {
            Thread thread = new Thread(controller, "Elevator-" + controller.getElevator().getId());
            thread.start();
            workerThreads.add(thread);
        }
        System.out.println(name + " started with " + elevatorControllers.size() + " elevators, floors "
                + minFloor + "-" + maxFloor);
    }

    public void shutdown() {
        for (Thread thread : workerThreads) {
            thread.interrupt();
        }
        for (Thread thread : workerThreads) {
            try {
                thread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public Floor getFloor(int floorNumber) {
        validateFloor(floorNumber);
        return floors.get(floorNumber - minFloor);
    }

    public ElevatorDispatcher getDispatcher() {
        return dispatcher;
    }

    public List<ElevatorController> getElevatorControllers() {
        return elevatorControllers;
    }

    private void validateFloor(int floorNumber) {
        if (floorNumber < minFloor || floorNumber > maxFloor) {
            throw new IllegalArgumentException("Invalid floor: " + floorNumber);
        }
    }
}
