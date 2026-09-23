package Adapter;

/*

it bridges the gap between existing interface and expected interface
allows incompatible interfaces to work together by converting the interface of one class
 into another that the client expects.


Interface translation: The adapter maps method calls from one interface to another, 
handling differences in method names, parameter types, return types, and calling conventions.
No source modification: Neither the client's expected interface nor the incompatible class
 is changed. The adapter wraps the incompatible class and presents the expected interface to the client.


 1. Object Adapter (Preferred)
Uses composition: the adapter holds a reference to the adaptee (the object it wraps).
Allows flexibility and reuse across class hierarchies.
This is the most common and recommended approach.

-------
Adapter has four participants.

Target Interface
The interface that the client code depends on. Every method call from the client goes
 through this interface.
In our payment example, PaymentProcessor is the Target. The checkout service only knows
 about processPayment(), isPaymentSuccessful(), and getTransactionId().

Adaptee
The existing class with a useful implementation but an incompatible interface.
In our example, LegacyGateway is the Adaptee. It can process payments, but its methods
 (executeTransaction(), checkStatus(), getReferenceNumber()) do not match what the checkout service expects.

Adapter
The translator. It implements the Target interface and holds a reference to the Adaptee,
 delegating calls with the necessary translation.
In our example, LegacyGatewayAdapter implements PaymentProcessor and wraps LegacyGateway,
 translating processPayment() into executeTransaction() and converting the long reference
  number into a String transaction ID.

Client
The code that uses the Target interface. It is completely unaware of the Adaptee or the 
Adapter's internal workings.

*/
interface PaymentProcessor {
    void processPayment(double amount, String currency);
    boolean isPaymentSuccessful();
    String getTransactionId();
}

class InHousePaymentProcessor implements PaymentProcessor {
    private String transactionId;
    private boolean paymentSuccessful;

    @Override
    public void processPayment(double amount, String currency) {
        System.out.println("InHouseProcessor: Processing " + amount + " " + currency);
        transactionId = "TXN_" + System.currentTimeMillis();
        paymentSuccessful = true;
        System.out.println("InHouseProcessor: Success. Txn ID: " + transactionId);
    }

    @Override
    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }
}

class CheckoutService {
    private final PaymentProcessor paymentProcessor;

    public CheckoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(double amount, String currency) {
        System.out.println("Checkout: Processing order for $" + amount + " " + currency);
        paymentProcessor.processPayment(amount, currency);
        if (paymentProcessor.isPaymentSuccessful()) {
            System.out.println("Checkout: Order successful! Txn: "
                + paymentProcessor.getTransactionId());
        } else {
            System.out.println("Checkout: Order failed.");
        }
    }
}

class LegacyGateway {
    private long transactionReference;
    private boolean paymentSuccessful;

    public void executeTransaction(double totalAmount, String currency) {
        System.out.println("LegacyGateway: Executing " + currency + " " + totalAmount);
        transactionReference = System.nanoTime();
        paymentSuccessful = true;
        System.out.println("LegacyGateway: Done. Ref: " + transactionReference);
    }

    public boolean checkStatus(long ref) {
        System.out.println("LegacyGateway: Checking status for ref: " + ref);
        return paymentSuccessful;
    }

    public long getReferenceNumber() {
        return transactionReference;
    }
}

class LegacyGatewayAdapter implements PaymentProcessor {
    private final LegacyGateway legacyGateway;
    private long currentRef;

    public LegacyGatewayAdapter(LegacyGateway legacyGateway) {
        this.legacyGateway = legacyGateway;
    }

    @Override
    public void processPayment(double amount, String currency) {
        System.out.println("Adapter: Translating processPayment() for " + amount + " " + currency);
        legacyGateway.executeTransaction(amount, currency);
        currentRef = legacyGateway.getReferenceNumber(); // Store for later use
    }

    @Override
    public boolean isPaymentSuccessful() {
        return legacyGateway.checkStatus(currentRef);
    }

    @Override
    public String getTransactionId() {
        return "LEGACY_TXN_" + currentRef;
    }
}

public class adapterPattern {
    public static void main(String[] args) {
        // Legacy gateway through adapter
        System.out.println("\n--- Using Legacy Gateway via Adapter ---");
        LegacyGateway legacy = new LegacyGateway();
        PaymentProcessor processor = new LegacyGatewayAdapter(legacy);
        CheckoutService legacyCheckout = new CheckoutService(processor);
        legacyCheckout.checkout(75.50, "USD");
    }
}
