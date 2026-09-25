package StockTrading;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final long id;
    private final String name;
    private final BankAccount bankAccount;
    private final Portfolio portfolio;

    private final List<WatchList> watchlists = new ArrayList<>();
    public User(long id, String name, BankAccount bankAccount) {
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
        this.portfolio = new Portfolio(id);
}

    public void addWatchlist(WatchList watchlist) {
        watchlists.add(watchlist);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public List<WatchList> getWatchlists() {
        return List.copyOf(watchlists);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
