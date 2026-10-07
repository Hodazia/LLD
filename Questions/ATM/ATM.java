package ATM;

public class ATM {
    int noOfTwoThousandNotes;
    int noOfFiveHundredNotes;
    int noOfOneHundredNotes;
    private int atmBalance;

    ATMState currentATMState;
    // create  a singleton class
    private static ATM atmobj = new ATM(); // eager initialization

    private ATM()
    {

    }

    public void setCurrentATMState(ATMState currentATMState)
    {
        this.currentATMState = currentATMState;
    }

    public ATMState getCurrentATMState()
    {
        return currentATMState;
    }

    public int getAtmBalance()
    {
        return atmBalance;
    }

    public static ATM getATMObject()
    {
        atmobj.setCurrentATMState(new IdleState());
        return atmobj;
    }

    public void setAtmBalance(int atmBalance, int noOfTwoThousandNotes, int noOfFiveHundredNotes, int noOfOneHundredNotes)
    {
        this.atmBalance = atmBalance;
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
        this.noOfOneHundredNotes = noOfOneHundredNotes;
    }

    public int getNoOfTwoThousandNotes()
    {
        return noOfTwoThousandNotes;
    }

    public int getNoOfFiveHundredNotes()
    {
        return noOfFiveHundredNotes;
    }

    public int getNoOfOneHundredNotes()
    {
        return noOfOneHundredNotes;
    }

    public void deductATMBalance(int amount) {
        atmBalance = atmBalance - amount;
    }

    public void deductTwoThousandNotes(int number) {
        noOfTwoThousandNotes = noOfTwoThousandNotes - number;
    }

    public void deductFiveHundredNotes(int number) {
        noOfFiveHundredNotes = noOfFiveHundredNotes - number;
    }

    public void deductOneHundredNotes(int number) {
        noOfOneHundredNotes = noOfOneHundredNotes - number;
    }

    public void printCurrentATMStatus() {
        System.out.println("Balance: " + atmBalance);
        System.out.println("2kNotes: " + noOfTwoThousandNotes);
        System.out.println("500Notes: " + noOfFiveHundredNotes);
        System.out.println("100Notes: " + noOfOneHundredNotes);
    }


}
