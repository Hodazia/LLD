package IRCTC;

public interface PaymentStrategy {
    boolean pay(String bookingId, double amount);
} 

class UpiPayment implements PaymentStrategy {
    public boolean pay(String bookingId, double amount) {
        System.out.println("Paying via UPI: " + amount);
        // Call external UPI gateway in production.
        return true;
    }
}

class CardPayment implements PaymentStrategy {
    public boolean pay(String bookingId, double amount) {
        System.out.println("Paying via card: " + amount);
        return true;
    }
}

class NetBankingPayment implements PaymentStrategy {
    public boolean pay(String bookingId, double amount) {
        System.out.println("Paying via net banking: " + amount);
        return true;
    }
}

class PaymentService {
    public boolean pay(String bookingId,double amount,PaymentStrategy strategy) {
        return strategy.pay(bookingId, amount);
    }
}
