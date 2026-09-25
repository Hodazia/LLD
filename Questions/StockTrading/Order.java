package StockTrading;

import java.time.Instant;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final long userId;
    private final Stock stock;
    private final int quantity;
    private final OrderType orderType;
    private final OrderSide side;
    private final ExchangeType exchange;
    private final Double limitPrice;
    private volatile OrderStatus status;
    private final Instant createdAt;

    public Order(long userId, Stock stock, int quantity,OrderType orderType,
        OrderSide side, ExchangeType exchange, Double limitPrice)
    {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be positive"
            );
        }

        if (orderType == OrderType.LIMIT && limitPrice == null) {
            throw new IllegalArgumentException(
                    "Limit order requires limit price"
            );
        }
        this.orderId=UUID.randomUUID().toString();
        this.userId = userId;
        this.stock = stock;
        this.quantity = quantity;
        this.orderType = orderType;
        this.side = side;
        this.exchange = exchange;
        this.limitPrice = limitPrice;

        this.status = OrderStatus.CREATED;
        this.createdAt = Instant.now();
    }


    public void markPending() {
        status = OrderStatus.PENDING;
    }

    public void markExecuted() {
        status = OrderStatus.EXECUTED;
    }

    public void markRejected() {
        status = OrderStatus.REJECTED;
    }

    public void cancel() {
        status = OrderStatus.CANCELLED;
    }

    public String getOrderId() {
        return orderId;
    }

    public long getUserId() {
        return userId;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public OrderSide getSide() {
        return side;
    }

    public ExchangeType getExchange() {
        return exchange;
    }

    public Double getLimitPrice() {
        return limitPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
