package Questions.InventoryManagement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    int categoryid;
    String categoryName;
    List<Product> products = new ArrayList<>();
    double price;

    // 
    public void addproduct(Product p)
    {
        products.add(p);
    }

    public void remove_product(Product p)
    {
        // find the index and remove
        int idx = products.indexOf(p);
        if(idx!=-1)
        {
            products.remove(idx);
        }
        else{
            System.out.println("No such product found");
        }
    }

    public void get_product(int pid)
    {
        // get a product by its id
        boolean found = false;
        for(Product z:products)
        {
            if(z.id == pid)
            {
                found = true;
                System.out.println("The product with id is " + z);
            }
        }
        if(!found)
        {
            System.out.println("No such product");
        }

    }
}
