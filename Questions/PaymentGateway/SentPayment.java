package PaymentGateway;
import java.util.List;

public class SentPayment extends Transaction {
    List<Receiver> receiversList;
    private long sendAt;
}

class ReceivePayment extends Transaction{
    private long receiveAt;
}


abstract class Receiver {
    private long amount;
    private String state;
    private String city;
    private String name;
}

// sending via bank account
class AccountReceiver extends Receiver {
    private String accountId;
    private String ifsc;
    private String branchName;
    private String fullVpa;
}

// sending via phonepe upi
class UserReceiver extends Receiver{   // phonepe info
    String upiId;
    String phoneNumber;
}