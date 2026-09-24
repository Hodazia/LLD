package DeliveryApp;

import java.util.List;
import java.util.ArrayList;


class CartItem {
    // suppose we have 2 Coke and 5 Lays, List<Item> won't be able to handle it
    private final Item item;
    private int quantity;

    public CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getTotalPrice() {
        return item.getPrice() * quantity;
    }
}

class Cart {
    private final int cartId;
    private Restaurant restaurant;
    private final List<CartItem> items;

    public Cart(int cartId) {
        this.cartId = cartId;
        this.items = new ArrayList<>();
    }

    void addItem(Restaurant restaurant, Item item)
    {
         // Cart can contain items from only one restaurant
         if (this.restaurant == null) {
            this.restaurant = restaurant;
        }

        if (this.restaurant.getRestaurantId()
                != restaurant.getRestaurantId()) {

            throw new IllegalStateException(
                    "Cart can contain items from only one restaurant"
            );
        }
        for (CartItem cartItem : items) {
            if (cartItem.getItem().getItemId() == item.getItemId()) {
                cartItem.increaseQuantity(1);
                return;
            }
        }

        items.add(new CartItem(item, 1));
    }

    public void clear() {
        items.clear();
        restaurant = null;
    }

    public int getTotal() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
}


public class User {
    private final int userid;
    private final String name;
    private final String address;
    private final Cart cart; // composition , 
    private final Location location;

    public User(int id,Cart cart,String name, String address,Location location){
        this.userid = id;
        this.cart = cart;
        this.name = name;
        this.address = address;
        this.location = location;
    }

    void addItem(Restaurant restaurant, Item item)
    {
        cart.addItem(restaurant, item);
    }
    public Cart getCart() {
        return cart;
    }

    public Location getLocation() {
        return location;
    }

    public int getUserId() {
        return userid;
    }

    public String getName() {
        return name;
    }

    void checkout()
    {
        // make a payment 
    }
}
