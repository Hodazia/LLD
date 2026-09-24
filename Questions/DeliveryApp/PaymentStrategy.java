package DeliveryApp;

public interface PaymentStrategy {
    public boolean pay();
}

class UPIPayment implements PaymentStrategy {

    @Override 
    public boolean pay()
    {
        System.out.println("Paid by UPI");
        return true;
    }
}

class CreditCardPayment implements PaymentStrategy {

    @Override 
    public boolean pay()
    {
        System.out.println("Paid by CreditCard");
        return true;
    }
}

class PaymentService {

    public boolean processPayment(
            int amount,
            PaymentStrategy paymentStrategy) {

        return paymentStrategy.pay();
    }
}

