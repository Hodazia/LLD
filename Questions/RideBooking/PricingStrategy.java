package RideBooking;

public interface PricingStrategy {
    double calculateFare(Location source,Location destination);
}

class NormalPricingStrategy implements PricingStrategy{

    private static final double BASE_FARE = 50;
    private static final double PER_DISTANCE = 15;

    @Override
    public double calculateFare(Location source,Location destination) 
    {
        double distance = source.distanceTo(destination);

        return BASE_FARE +
                distance * PER_DISTANCE;
    }
}

class SurgePricingStrategy implements PricingStrategy {

    private final double surgeMultiplier;

    public SurgePricingStrategy(double surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public double calculateFare(Location source,Location destination)
     {
        double baseFare =50 + source.distanceTo(destination) * 15;
        return baseFare * surgeMultiplier;
    }
}