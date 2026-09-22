package CarRentalSystem;

enum BillStatus {
    PAID, 
    UNPAID
}

public class Bill {
    private final String id;
    private final Reservation reservation;
    private final double amount;

    private BillStatus status;

    public Bill(
            String id,
            Reservation reservation,
            double amount) {

        this.id = id;
        this.reservation = reservation;
        this.amount = amount;
        this.status = BillStatus.UNPAID;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public BillStatus getStatus() {
        return status;
    }
    public void markPaid() {
        status = BillStatus.PAID;
    }
}
