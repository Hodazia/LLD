package Questions.Elevator;

/**
 Demo scenario:
 1. User at floor 0 presses UP
 2. User at floor 5 presses DOWN
 3. Passenger inside elevator 1 selects floor 8
 4. User at floor 2 presses UP while elevator is busy (LOOK queues it correctly)
 */
public class main {
    public static void main(String[] args) throws InterruptedException {
        Building building = new Building("TechPark Tower", 0, 9, 3);
        building.start();

        Thread.sleep(500);

        System.out.println("\n--- Scenario 1: External UP from floor 0 ---");
        building.getFloor(0).pressUpButton();

        Thread.sleep(800);

        System.out.println("\n--- Scenario 2: External DOWN from floor 5 ---");
        building.getFloor(5).pressDownButton();

        Thread.sleep(800);

        System.out.println("\n--- Scenario 3: Internal request to floor 8 in elevator 1 ---");
        new InternalButton(1, 8, building.getDispatcher()).press();

        Thread.sleep(800);

        System.out.println("\n--- Scenario 4: External UP from floor 2 (opposite direction test) ---");
        building.getFloor(2).pressUpButton();

        Thread.sleep(12000);

        System.out.println("\n--- Final state ---");
        for (ElevatorController controller : building.getElevatorControllers()) {
            System.out.println(controller.getElevator());
        }

        building.shutdown();
    }
}
