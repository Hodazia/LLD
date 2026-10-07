package ATM;

public abstract class CashWithdrawlProcessor {
    CashWithdrawlProcessor nexCashWithdrawlProcessor;

    CashWithdrawlProcessor(CashWithdrawlProcessor cashWithdrawlProcessor)
    {
        this.nexCashWithdrawlProcessor = nexCashWithdrawlProcessor;
    }

    public void withdraw(ATM atm, int remainingAmount)
    {
        if(nexCashWithdrawlProcessor != null)
        {
            nexCashWithdrawlProcessor.withdraw(atm,remainingAmount);
        }
    }
}

class TwoThousandProcessor extends CashWithdrawlProcessor {
    TwoThousandProcessor(CashWithdrawlProcessor nexCashWithdrawlProcessor)
    {
        super(nexCashWithdrawlProcessor);
    }

    public void withdraw(ATM atm, int remainingAmount)
    {
        int required = remainingAmount/2000;
        int balance = remainingAmount%2000;

        if(required <= atm.getNoOfTwoThousandNotes())
        {
            atm.deductTwoThousandNotes(required);
        }
        else if(required > atm.getNoOfTwoThousandNotes())
        {
            atm.deductTwoThousandNotes(atm.getNoOfTwoThousandNotes());
            balance = balance + (required - atm.getNoOfTwoThousandNotes());
        }

        if(balance!=0)
        {
            super.withdraw(atm, balance);
        }
    }
}

class FiveHundredProcessor extends CashWithdrawlProcessor {
    FiveHundredProcessor(CashWithdrawlProcessor nexCashWithdrawlProcessor)
    {
        super(nexCashWithdrawlProcessor);
    }

    public void withdraw(ATM atm, int remainingAmount)
    {
        int required = remainingAmount/500;
        int balance = remainingAmount%500;

        if(required <= atm.getNoOfTwoThousandNotes())
        {
            atm.deductTwoThousandNotes(required);
        }
        else if(required > atm.getNoOfTwoThousandNotes())
        {
            atm.deductTwoThousandNotes(atm.getNoOfTwoThousandNotes());
            balance = balance + (required - atm.getNoOfTwoThousandNotes());
        }

        if(balance!=0)
        {
            super.withdraw(atm, balance);
        }
    }
}


class OneHundredProcessor extends CashWithdrawlProcessor {
    OneHundredProcessor(CashWithdrawlProcessor nexCashWithdrawlProcessor)
    {
        super(nexCashWithdrawlProcessor);
    }

    public void withdraw(ATM atm, int remainingAmount)
    {
        int required = remainingAmount/100;
        int balance = remainingAmount%100;

        if(required <= atm.getNoOfTwoThousandNotes())
        {
            atm.deductTwoThousandNotes(required);
        }
        else if(required > atm.getNoOfTwoThousandNotes())
        {
            atm.deductTwoThousandNotes(atm.getNoOfTwoThousandNotes());
            balance = balance + (required - atm.getNoOfTwoThousandNotes());
        }

        if(balance!=0)
        {
            super.withdraw(atm, balance);
        }
    }
}