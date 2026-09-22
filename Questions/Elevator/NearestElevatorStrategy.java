package Questions.Elevator;

import java.util.List;

/**
 * Picks the best elevator for an external request.
 * Prefers idle elevators, then same-direction elevators, then nearest distance.
 */
public class NearestElevatorStrategy implements ElevatorDispatchStrategy {
    @Override
    public ElevatorController select(List<ElevatorController> controllers, ElevatorRequest request) {
        ElevatorController best = null;
        int bestScore = Integer.MAX_VALUE;

        for (ElevatorController controller : controllers) {
            int score = score(controller, request);
            if (score < bestScore) {
                bestScore = score;
                best = controller;
            }
        }
        return best;
    }

    private int score(ElevatorController controller, ElevatorRequest request) {
        Elevator elevator = controller.getElevator();
        int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
        int score = distance * 10;

        if (elevator.getStatus() == ElevatorStatus.IDLE) {
            score -= 50;
        }

        if (elevator.getDirection() == request.getDirection()) {
            score -= 20;
        } else if (elevator.getDirection() != Direction.IDLE
                && elevator.getDirection() != request.getDirection()) {
            score += 30;
        }

        return score;
    }
}
