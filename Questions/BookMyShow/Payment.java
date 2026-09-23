package BookMyShow;

interface PaymentStrategy {
    void pay(double amount);
}

class UpiPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via UPI");
    }
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via Credit Card");
    }
}

class PaymentFactory {
    static PaymentStrategy create(PaymentType type) {
        switch (type) {
            case UPI:
                return new UpiPayment();
            case CREDIT_CARD:
                return new CreditCardPayment();
            default:
                throw new IllegalArgumentException("Unknown payment type");
        }
    }
}
