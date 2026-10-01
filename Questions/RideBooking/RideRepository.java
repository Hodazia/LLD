package RideBooking;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface RideRepository {
    void save(Ride ride);
    Optional<Ride> findById(String id);  
} 

class InMemoryRideRepository implements RideRepository {
    private final Map<String, Ride> rides =new ConcurrentHashMap<>();

    @Override
    public void save(Ride ride) {
        rides.put(ride.getId(), ride);
    }

    @Override
    public Optional<Ride> findById(String id) {
        return Optional.ofNullable(rides.get(id));
    }
}