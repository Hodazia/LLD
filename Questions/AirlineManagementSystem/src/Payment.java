public class Payment {
    private final String paymentId;
    private final String paymentMethod;
    private final double amount;
    private PaymentStatus status;

    public Payment(String paymentId, String paymentMethod, double amount) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }

    public void processPayment() {
        // Process payment logic
        status = PaymentStatus.COMPLETED;
    }
}

class PaymentProcessor {
    private static PaymentProcessor instance;

    private PaymentProcessor()
    {

    }

    public static synchronized PaymentProcessor getInstance()
    {
        if (instance == null) {
            instance = new PaymentProcessor();
        }
        return instance;
    }

    public void processPayment(Payment payment) {
        // Process payment using the selected payment method
        payment.processPayment();
    }
}
