package CarRentalSystem;

enum PaymentType {
    CREDIT_CARD,
    UPI
}

public interface Payment {
    void pay(Bill bill);
}

class CreditCard implements Payment {
    @Override
    public void pay(Bill bill) {
        System.out.println("Paid ₹" + bill.getAmount() + " using card");
    }
}

class UPIPayment implements Payment {
    @Override
    public void pay(Bill bill) {
        System.out.println("Paid ₹" + bill.getAmount() + " using UPI");
    }
}

class PaymentFactory {
    static Payment createPayment(PaymentType paymentType) {
        switch (paymentType) {
            case CREDIT_CARD:
                return new CreditCard();
            case UPI:
                return new UPIPayment();
            default:
                throw new IllegalArgumentException("Unknown payment type");
        }
    }
}
