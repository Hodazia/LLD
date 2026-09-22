package DESIGN_PATTERNS.Decorator;

interface Smartphones {
    public double getCost();
    public String getColor();
    public int getmemeory();
}

class Smartphone implements  Smartphones {
    @Override
    public double getCost()
    {
        return 10500.5;
    }

    @Override 
    public String getColor()
    {
        return "White";
    }

    @Override
    public int getmemeory()
    {
        return 8;
    }
}


//Decorator
abstract class SmartphoneDecorator implements Smartphones {
    protected Smartphones smrtphne;

    public SmartphoneDecorator(Smartphones smartphne) {
        this.smrtphne = smartphne;
    }

    public double getCost()
    {
        return smrtphne.getCost();
    }

    public String getColor()
    {
        return smrtphne.getColor();
    }

    public int getmemeory()
    {
        return smrtphne.getmemeory();
    }
}

public class IPhoneDecorator extends SmartphoneDecorator {
    public IPhoneDecorator(Smartphones smrt) {
        super(smrt);
    }

    @Override
    public double getCost() {
        return super.getCost() * 10.0;
    }

    @Override
    public String getColor() {
        return "Blue";
    }

    @Override
    public int getmemeory() {
        return super.getmemeory() + 8;
    }
}

/*
interface,
concrete class,
abstract class implement niterfcae,
concrete decorator, extend the functionality of decorator

*/