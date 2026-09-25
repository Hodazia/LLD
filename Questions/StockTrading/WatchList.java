package StockTrading;

import java.util.HashMap;
import java.util.Map;

public class WatchList {
    // user can have multiple watchlists
    private final long id;
    private final String name;

    private final Map<Long,Stock> stocks = new HashMap<>();

    public WatchList(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addStock(Stock stock) {
        stocks.put(stock.getStockId(), stock);
    }

    public void removeStock(Stock stock) {
        stocks.remove(stock.getStockId());
    }

    public Map<Long, Stock> getStocks() {
        return Map.copyOf(stocks);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
