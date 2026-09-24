package DeliveryApp;


public class Location {
    // we can add latitude, longititude also , and a geo-indexing system
    private double x;
    private double y;

    Location(double x, double y)
    {
        this.x = x;
        this.y= y;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    void set_Location(int newx,int newy)
    {
        this.x = newx;
        this.y = newy;
    }

    public double distanceTo(Location other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;

        return Math.sqrt(dx * dx + dy * dy);
    }
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
