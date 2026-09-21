package Questions.InventoryManagement;


import java.util.Map;

public class Order {
    User user;
    Address deliveryAddress;
    Map<Integer,Integer> productCategoryAndCountMap;
    Warehouse warehouse;
    Invoice invoice;

    Order(User user, Warehouse warehouse)
    {
        this.user = user;
        this.productCategoryAndCountMap = user.getUserCart().getCartItems();
        this.warehouse = warehouse;
        this.deliveryAddress = user.address;
        invoice = new  Invoice();
        invoice.generateInvoice(this);
    }

    public void checkout()
    {

    }
}


class Invoice {
    int totalItemPrice;
    int totalTax;
    int totalFinalPrice;

    public void generateInvoice(Order order)
    {
        totalItemPrice = 200;
        totalTax = 20;
        totalFinalPrice = 220;
    }


}