package BookMyShow;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private final String bookingId;
    private final User user;
    private Show show;
    private final List<Seat> bookedSeats = new ArrayList<>();
    private double totalAmount;
    private PaymentType paymentType;
    private boolean paid;

    public Booking(String bookingId, User user) {
        this.bookingId = bookingId;
        this.user = user;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isPaid() {
        return paid;
    }

    public void markPaid() {
        this.paid = true;
    }
}
