package DeliveryApp;

public class Item {
    private final int itemId;
    private final String name;
    private final int price;

    Item(int itemid, int pri,String name)
    {
        this.itemId = itemid;
        this.price = pri;
        this.name = name;
    }

    public int getItemId()
    {
        return this.itemId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
