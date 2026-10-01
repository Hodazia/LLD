package IRCTC;

import java.util.Optional;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
example of how we are gonna use it,

Train train = new Train(
    "12301",
    "Rajdhani Express",
    coaches
);

trainRepository.save(train);

Train result = trainRepository
    .findByNumber("12301")
    .orElseThrow();

*/
public interface TrainRepository {
    void save(Train train);
    Optional<Train> findByNumber(String trainNumber);
}

class InMemoryTrainRepository implements TrainRepository {
    private final Map<String, Train> trains = new ConcurrentHashMap<>();

    public void save(Train train) {
        trains.put(train.trainNumber, train);
    }

    public Optional<Train> findByNumber(String number) {
        return Optional.ofNullable(trains.get(number));
    }
}
