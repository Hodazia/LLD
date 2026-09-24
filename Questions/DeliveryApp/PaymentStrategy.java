package DeliveryApp;

public interface PaymentStrategy {
    public void pay();
}

class UPIPayment implements PaymentStrategy {

    @Override 
    public void pay()
    {
        System.out.println("Paid by UPI");
    }
}

class CreditCardPayment implements PaymentStrategy {

    @Override 
    public void pay()
    {
        System.out.println("Paid by CreditCard");
    }
}



