package RideBooking;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Comparator;


public interface DriverRepository {
    void save(Driver driver);
    Optional<Driver> findById(String id); // we can use List<Driver> also , if we do not find it will return null
    List<Driver> findNearbyAvailableDrivers(Location location);
}

class InMemoryDriverRepository implements DriverRepository {

    private final Map<String, Driver> drivers = new ConcurrentHashMap<>();
    // we can use just a hashmap also

    @Override
    public void save(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    @Override
    public Optional<Driver> findById(String id) {
        return Optional.ofNullable(drivers.get(id));
    }

    @Override
    public List<Driver> findNearbyAvailableDrivers(Location location) {

        return drivers.values()
                .stream()
                .filter(Driver::isAvailable)
                .filter(driver ->
                        driver.getLocation()
                               .distanceTo(location) <= 10)
                .sorted(Comparator.comparingDouble(
                        driver ->
                                driver.getLocation()
                                       .distanceTo(location)))
                .toList();
    }
}