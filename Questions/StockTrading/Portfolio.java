package StockTrading;

import java.util.Map;
import java.util.HashMap;

public class Portfolio {
    private final long userId;
    private final Map<Long, Holding> holdings = new HashMap<>();

    public Portfolio(long userId) {
        this.userId = userId;
    }

    public synchronized void addLot(Lot lot) {

        Holding holding =
                holdings.computeIfAbsent(
                        lot.getStock().getStockId(),
                        id -> new Holding(lot.getStock())
                );

        holding.addLot(lot);
    }

    public synchronized Holding getHolding(long stockId) {
        return holdings.get(stockId);
    }

    public synchronized int getQuantity(long stockId) {

        Holding holding = holdings.get(stockId);
        if (holding == null) {
            return 0;
        }
        return holding.getTotalQuantity();
    }

    public Map<Long, Holding> getHoldings() {
        return Map.copyOf(holdings);
    }
}
