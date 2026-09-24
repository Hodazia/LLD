package DeliveryApp;

public class DeliveryAgent {
    int id;
    String address;
    Location location;
    Order order;
    boolean accept;

    DeliveryAgent(int id,String add, Location loc)
    {
        this.id = id;
        this.address = address;
        this.location = location;
        this.order = null;
        this.accept = false;
    }

    void accept_order(Order e)
    {
        // for now accept one order
        if(order!=null)
        {
            this.order = e;
            accept = true;
        }
    }

    void calculate_distance()
    {
        // find the distance between current driever location and restaurant location
    }


}
