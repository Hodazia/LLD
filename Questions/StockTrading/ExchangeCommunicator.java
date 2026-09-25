package StockTrading;

public interface ExchangeCommunicator {
    public ExecutionResult execute(Order order, double marketPrice);
}

class ExecutionResult {

    private final boolean success;
    private final double executionPrice;

    public ExecutionResult(boolean success, double executionPrice) {
        this.success = success;
        this.executionPrice = executionPrice;
    }

    public boolean isSuccess() {
        return success;
    }

    public double getExecutionPrice() {
        return executionPrice;
    }
}

class NSEExchangeCommunicator implements ExchangeCommunicator {

    @Override
    public ExecutionResult execute(Order order,double marketPrice) {

        System.out.println("Sending order " + order.getOrderId()+ " to NSE");
        return new ExecutionResult(true,marketPrice);
    }
}

class BSEExchangeCommunicator implements ExchangeCommunicator {

    @Override
    public ExecutionResult execute(Order order,double marketPrice) {

        System.out.println("Sending order " + order.getOrderId()+ " to BSE");
        return new ExecutionResult(true,marketPrice);
    }
}