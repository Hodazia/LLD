package vending_machine;

public class Prodcut {
    String productid;
    double price;

    void Product(String prid, double p)
    {
        this.productid = prid;
        this.price = p;
    }

    void update_price(double pr)
    {
        this.price = pr;
    }
}

/* 
main class -> factory -> concrete factory create concret product

2 interfaces product and factory,

MacOs, Linux , Windows, 
OS , 



*/ 