package IRCTC;

import IRCTC.enums.TicketType;
import java.util.UUID;
    
interface Ticket {
    String getTicketId();
    TicketType getTicketType();
    double getFare();
}

class ConfirmedTicket implements Ticket {
    private final String ticketId;
    private final double fare;

    ConfirmedTicket(String ticketId, double fare) {
        this.ticketId = ticketId;
        this.fare = fare;
    }

    public String getTicketId() { return ticketId; }
    public TicketType getTicketType() {
        return TicketType.CONFIRMED;
    }
    public double getFare() { return fare; }
}

class TatkalTicket implements Ticket {
    private final String ticketId;
    private final double fare;

    TatkalTicket(String ticketId, double fare) {
        this.ticketId = ticketId;
        this.fare = fare;
    }

    public String getTicketId() { return ticketId; }
    public TicketType getTicketType() {
        return TicketType.TATKAL;
    }
    public double getFare() { return fare; }
}

// Factory Pattern
public class TicketFactory {

    public Ticket createTicket(TicketType type, double baseFare) {

        String ticketId = UUID.randomUUID().toString();

        return switch (type) {
            case CONFIRMED ->
                    new ConfirmedTicket(ticketId, baseFare);

            case TATKAL ->
                    new TatkalTicket(
                            ticketId, baseFare * 1.5);
        };
    }
}
