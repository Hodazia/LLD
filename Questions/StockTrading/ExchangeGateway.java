package StockTrading;

import java.util.Map;
import java.util.HashMap;

public class ExchangeGateway {
    // NSE, NSE EXchangeCommunicator
    private final Map<ExchangeType, ExchangeCommunicator> exchanges;

    public ExchangeGateway(
            Map<ExchangeType,ExchangeCommunicator> exchanges) {
        this.exchanges = exchanges;
    }

    public ExecutionResult execute( Order order, double marketPrice) {
        ExchangeCommunicator exchange = exchanges.get(order.getExchange());
        if (exchange == null) {
            throw new IllegalArgumentException("Unsupported exchange");
        }

        return exchange.execute(order, marketPrice);
    }
}
