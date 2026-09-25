package StockTrading;

public class Stock {
    private final long stockId;
    private final String symbol;
    private final String companyName;

    public Stock(long stockId, String symbol, String companyName) {
        this.stockId = stockId;
        this.symbol = symbol;
        this.companyName = companyName;
    }

    public long getStockId() {
        return stockId;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Stock)) return false;
        Stock other = (Stock) obj;
        return stockId == other.stockId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(stockId);
    }
}


class StockQuote {
    // this will be independent and we will use this to update the price and tell the user
    /*
    if the user does a limit sell , then if the stock price becomes the limit price,
    then we send notification to the user and the broker can sell the user stock
    */
    private final Stock stock;
    private final ExchangeType exchange;
    private double currentPrice;
    private double previousDayPrice;

    public StockQuote(Stock stock,ExchangeType exchange, double currentPrice,double previousDayPrice) {
        this.stock = stock;
        this.exchange = exchange;
        this.currentPrice = currentPrice;
        this.previousDayPrice = previousDayPrice;
    }

    public synchronized void updatePrice(double price) {
        // 
        this.currentPrice = price;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getChange() {
        return currentPrice - previousDayPrice;
    }

    public double getChangePercentage() {

        if (previousDayPrice == 0) {
            return 0;
        }
        return ((currentPrice - previousDayPrice)
                / previousDayPrice) * 100;
    }

    public Stock getStock() {
        return stock;
    }

    public ExchangeType getExchange() {
        return exchange;
    }
}


class StockItem {
    // when a user buys he buys in quantities not just 1
    private final Stock stock;
    private final int quantity;

    public StockItem(Stock stock, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be positive"
            );
        }
        this.stock = stock;
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }
}