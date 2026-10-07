package PaymentGateway;

import java.util.List;

import PaymentGateway.Instrunment.Instrunment;

public class Transaction {
    private String transactionId;
    private TransactionType type;
    private String globalPaymentId;
    private PaymentMode mode;
    private TransactionState state;
    private List<Instrunment> paymentInstrunment;
    private String paymentReferenceId;// sentPayment id.
    private String notes; /* Txn notes */
    private long amount;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
}


enum TransactionType {
    SENT_PAYMENT, RECEIVE_PAYMENT, MUTUAL_FUND_REDEMPTION
}

enum TransactionState {
    COMPLETED, PENDING, FAILED
}

enum PaymentMode {
    UPI, QR
}