package DESIGN_PATTERNS.Strategy;

interface PaymentStrategy{
    void pay(int amount);
}

class CreditCardStrategy implements  PaymentStrategy {
    private String cardNumber;
    public CreditCardStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Credit Card (" + cardNumber + ").");
    }
}


class PayPalStrategy implements PaymentStrategy {
    private String email;

    public PayPalStrategy(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using PayPal (" + email + ").");
    }
}


class ShoppingCart {
    // Reference to the strategy interface
    private PaymentStrategy paymentStrategy;

    // Allows setting or changing the strategy dynamically
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not selected!");
        }
        // Delegate the work to the selected strategy
        paymentStrategy.pay(amount);
    }
}

public class Strtgy {
    public static void main(String args[])
    {
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new CreditCardStrategy("12334"));
        cart.checkout(2000);

        cart.setPaymentStrategy(new PayPalStrategy("zia23hoda@gmail.com"));
        cart.checkout(2000);
    }   
}
