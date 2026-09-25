package StockTrading;

import java.time.Instant;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Holding {
    // how much shares of the stock do i currently own

    private final Stock stock;
    private final List<Lot> lots = new ArrayList<>();
    public Holding(Stock stock) {
        this.stock = stock;
    }

    public synchronized void addLot(Lot lot) {
        lots.add(lot);
    }

    public synchronized int getTotalQuantity() {

        int total = 0;
        for (Lot lot : lots) {
            total += lot.getQuantity();
        }
        return total;
    }

    public synchronized List<Lot> getLots() {
        return Collections.unmodifiableList(
                new ArrayList<>(lots)
        );
    }

    public Stock getStock() {
        return stock;
    }

}

// Lot -> which particular purchase created these shares
/*

suppose 3 purchases happended at different prices:
Buy 10 Reliance @ ₹2,000
Buy 20 Reliance @ ₹2,200
Buy 5  Reliance @ ₹2,100

internally u have 3 lots,
Reliance Holding
├── Lot 1
│   10 shares @ ₹2,000
├── Lot 2
│   20 shares @ ₹2,200
└── Lot 3
    5 shares @ ₹2,100
*/


class Lot {

    private final long lotId;
    private final Stock stock;
    private final int quantity;
    private final double purchasePrice;
    private final Instant purchaseTime;

    public Lot(long lotId,Stock stock,int quantity,double purchasePrice) {

        this.lotId = lotId;
        this.stock = stock;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.purchaseTime = Instant.now();
    }

    public long getLotId() {
        return lotId;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public Instant getPurchaseTime() {
        return purchaseTime;
    }
}