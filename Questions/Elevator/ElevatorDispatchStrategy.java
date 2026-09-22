package Questions.Elevator;

import java.util.List;

public interface ElevatorDispatchStrategy {
    ElevatorController select(List<ElevatorController> controllers, ElevatorRequest request);
}
