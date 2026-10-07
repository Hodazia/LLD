package PaymentGateway.Instrunment;

import PaymentGateway.Transaction.TransactionType;

public abstract class Instrunment {
    TransactionState state;
    long amount;
    InstrunmentType type;

    public InstrunmentType getInstrunmentType()
    {
        return type;
    }

    public void setInstrunmentType(InstrunmentType type)
    {
        this.type = type;
    }
}


enum InstrunmentType {
    CARD, 
    BANK,
    WALLET,
}

class Account extends Instrunment {
    long id;
    String bankAccountNumber;
    String ifscCode;
    String bankNumber;
    String accType; // savings, FD, current
    String branchName;
}

class Wallet extends Instrunment {
    String walletID;
    String availableBalance;
}