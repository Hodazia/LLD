package Facade;


/*
Think of a home theater remote control. Instead of turning on the television, 
switching inputs, powering on the sound system, and launching a streaming device 
one by one, you press a single "Watch Movie" button. The remote control acts as a facade,
 hiding all the complex internal steps

Core Components
• Facade: A single class or object that exposes high-level, easy-to-use methods. 
It knows which subsystem classes handle specific requests and delegates client calls accordingly.
• Subsystem Classes: A complex set of classes or services that perform the actual work. 
They are unaware that a facade exists.
• Client: The code that needs to use the subsystem. It talks only to the facade, 
avoiding direct interaction with messy internal components.


Imagine you have a multi service architecture and these services interact with each other.
 If there are multiple clients who wants to interact with these services,
  they have to setup connection with all individual services.

This design is very chaotic, difficult to maintain and not scalable.
To better manage the interaction of clients with these services, we add a facade layer 
which will interact with all these services. And clients will only interact with facade layer.
 This pattern will help easy integration of clients to the system, abstract out the low level
  details of service’s from clients and it will be easy maintain and scale.

CLIENT IS NOT AWARE OF PaymentValidation, GatewayConnector, TransactionLogger
*/

class PaymentValidation {
    public boolean validate(String cardDetails, double amount) {
        System.out.println("Validating card and amount: " + amount);
        return true; 
    }
}

class GatewayConnector {
    public void sendToBank(String details, double amount) {
        System.out.println("Connecting to external bank API...");
    }
}

class TransactionLogger {
    public void logTransaction(String status) {
        System.out.println("Logging transaction status: " + status);
    }
}

class PaymentFacade {

    private PaymentValidation validator;
    private GatewayConnector connector;
    private TransactionLogger logger; 
    public PaymentFacade() {
        this.validator = new PaymentValidation();
        this.connector = new GatewayConnector();
        this.logger = new TransactionLogger();
    } 
    public void processPayment(String cardDetails, double amount) {
        if (validator.validate(cardDetails, amount)) {
            connector.sendToBank(cardDetails, amount);
            logger.logTransaction("SUCCESS");
            System.out.println("Payment completed successfully!");
        } else {
            logger.logTransaction("FAILED");
        }
    }
}

public class facadePattern {
    public static void main(String[] args) {
        PaymentFacade paymentService = new PaymentFacade();
        
        // Single method call handles the entire complex process
        paymentService.processPayment("1234-5678-9012-3456", 250.00);
    }
}
