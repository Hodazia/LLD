package StockTrading;

public class BankAccount {
    private final long id;
    private final BankType bankType;
    private double balance;

    public BankAccount(long id, BankType bankType, double balance)
    {
        this.id = id;
        this.bankType = bankType;
        this.balance = balance;
    }

    public synchronized void deposit(double amount)
    {
        if(amount<=0)
        {
            throw new IllegalArgumentException("invalid deposit");
        }
        balance += amount;
    }

    public synchronized void withdraw(double amount)
    {
        if(amount>balance)
        {
            throw new IllegalArgumentException("Balance not enough");
        }
        balance -= amount;
    }

    public synchronized double getBalance() {
        return balance;
    }

}
