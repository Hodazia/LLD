package IRCTC;

import java.util.Optional;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import IRCTC.enums.JourneyStatus;

public interface JourneyRepository {
    void save(TrainJourney journey);
    Optional<TrainJourney> findById(String journeyId);
    List<TrainJourney> search(String source, String destination, LocalDate date);
}

class InMemoryJourneyRepository implements JourneyRepository {
    private final Map<String, TrainJourney> journeys = new ConcurrentHashMap<>();

    public void save(TrainJourney journey) {
        journeys.put(journey.journeyId, journey);
    }

    public Optional<TrainJourney> findById(String id) {
        return Optional.ofNullable(journeys.get(id));
    }

    public List<TrainJourney> search( String source, String destination, LocalDate date) {
        return journeys.values().stream()
                .filter(j -> j.status == JourneyStatus.SCHEDULED)
                .filter(j -> j.source.equalsIgnoreCase(source))
                .filter(j -> j.destination.equalsIgnoreCase(destination))
                .filter(j -> j.journeyDate.equals(date))
                .toList();
    }
}