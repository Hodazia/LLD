package RideBooking;

public class Driver {

        private final String id;
        private final String name;
        private final Vehicle vehicle;
        private Location location;
        private boolean online;
        private boolean available;
        private Ride currentRide;
    
        public Driver(String id,String name,Vehicle vehicle,Location location)
        {
            this.id = id;
            this.name = name;
            this.vehicle = vehicle;
            this.location = location;
            this.online = false;
            this.available = false;
        }
    
        public String getId() {
            return id;
        }
    
        public Location getLocation() {
            return location;
        }
    
        public synchronized void goOnline() {
            online = true;
            available = true;
        }
    
        public synchronized void goOffline() {
            if (currentRide != null) {
                throw new IllegalStateException(
                        "Cannot go offline during active ride"
                );
            }
    
            online = false;
            available = false;
        }
    
        public synchronized void updateLocation(Location location) {
            this.location = location;
        }
    
        public synchronized boolean isAvailable() {
            return online && available;
        }
    
        /**
         * Critical section.
         *
         * Check + assign happens atomically.
         */
        public synchronized boolean tryAssignRide(Ride ride) {
    
            if (!online || !available || currentRide != null) {
                return false;
            }
    
            this.currentRide = ride;
            this.available = false;
    
            return true;
        }
    
        public synchronized void releaseRide() {
            this.currentRide = null;
    
            if (online) {
                this.available = true;
            }
        }
    
        public synchronized Ride getCurrentRide() {
            return currentRide;
        }
    
        public Vehicle getVehicle() {
            return vehicle;
        }
    }

/*

thread 1 -> Rider A requests ride
thread 2 -> Rider B requests ride,
without synchronization, both will find that ride is available for a given driver
with synchronized, only one thread can execute this critical section at a time.


*/