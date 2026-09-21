package Questions.InventoryManagement;

import java.util.Map;


// manages the inventory or acts as an inventory controller
public class Warehouse {
    Inventory inventory;
    Address adr;

    public void removeItemFromInventory(Map<Integer, Integer> ProductCategoryAndCountMap)
    {

    }

    public void addItemFromInventory(Map<Integer, Integer> ProductCategoryAndCountMap)
    {

    }
    
}

class Address {
    int pincode;
    String city;
    String state;

    Address(int pinCode, String city, String state)
    {
        this.pincode = pinCode;
        this.city = city;
        this.state = state;
    }

    //getters and setters
}
