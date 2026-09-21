package Questions.InventoryManagement;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<ProductCategory> inv;

    Inventory()
    {
        inv = new ArrayList<>();
    }

    // add new category
    void add_new_category(int categoryid, String name, double price)
    {
        ProductCategory pc1 = new ProductCategory();
        pc1.categoryid = categoryid;
        pc1.categoryName = name;
        pc1.price = price;
    }

    public void addProduct(Product product, int ProductCategoryId)
    {
        ProductCategory categoryObject = null;
        for(ProductCategory category: inv)
        {
            if(category.categoryid == ProductCategoryId)
            {
                categoryObject = category;
            }
        }

        if(categoryObject != null)
        {
            categoryObject.addproduct(product);
        }
    }

    void remove_pc(ProductCategory pc)
    {
         // find the index and remove
         int idx = inv.indexOf(pc);
         if(idx!=-1)
         {
             inv.remove(idx);
         }
         else{
             System.out.println("No such product found");
         }
    }
}
