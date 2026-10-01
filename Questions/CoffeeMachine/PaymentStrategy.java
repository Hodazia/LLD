package CoffeeMachine;

public interface PaymentStrategy {
    boolean pay(double amount);
    void refund(double amount);
    String getName();
}

class CashPayment implements PaymentStrategy {
    private double balance = 0;

    @Override
    public boolean pay(double amount) {
        balance += amount;
        return true;
    }

    @Override
    public void refund(double amount) {
        balance -= amount;

        if (balance < 0) {
            balance = 0;
        }

        System.out.println("Refunded ₹" + amount);
    }

    @Override
    public String getName() {
        return "Cash";
    }
}

class CardPayment implements PaymentStrategy {

    @Override
    public boolean pay(double amount) {

        System.out.println(
                "Charged card ₹" + amount
        );

        return true;
    }

    @Override
    public void refund(double amount) {

        System.out.println(
                "Refunded ₹" + amount + " to card"
        );
    }

    @Override
    public String getName() {
        return "Card";
    }
}

class UPIPayment implements PaymentStrategy {

    @Override
    public boolean pay(double amount) {

        System.out.println(
                "Paid ₹" + amount + " using UPI"
        );

        return true;
    }

    @Override
    public void refund(double amount) {

        System.out.println(
                "Refunded ₹" + amount + " to UPI"
        );
    }

    @Override
    public String getName() {
        return "UPI";
    }
}