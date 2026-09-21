package Questions.InventoryManagement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class User {
    int userId;
    String userName;
    Address address;
    Cart userCartDetails;
    List<Integer> orderId;

    public  User()
    {
        userCartDetails = new Cart();
        orderId = new ArrayList<>();
    }

    public Cart getUserCart()
    {
        return userCartDetails;
    }
}


class Cart{

    Map<Integer, Integer> productCategoryIdvsCountMap;

    Cart()
    {
        productCategoryIdvsCountMap = new HashMap<>();
    }

    // add item in cart
    void addItemInCart(int productCategoryId, int count)
    {
        if(productCategoryIdvsCountMap.containsKey(productCategoryId))
        {
            int noofItemsinCart = productCategoryIdvsCountMap.get(productCategoryId);
            productCategoryIdvsCountMap.put(productCategoryId, noofItemsinCart + count);
        }
        else{
            productCategoryIdvsCountMap.put(productCategoryId, count);
        }

    }

    // remove item in cart
    void removeItemInCart()
    {

    }

    public void emptyCart() 
    {
        productCategoryIdvsCountMap = new HashMap<>();
    }

    public Map<Integer,Integer> getCartItems()
    {
        return productCategoryIdvsCountMap;
    }

}

// management of user class 
class UserController {
    List<User> userList;
    UserController(List userList)
    {
        this.userList = userList;
    }

    public void addUser(User user)
    {
        userList.add(user);
    }

    public void removeUser(User user)
    {
        userList.remove(user);
    }

    public User getUser(int userId)
    {
        for(User user: userList)
        {
            if(user.userId == userId)
            {
                return user;
            }
        }
        return null;
    }
}