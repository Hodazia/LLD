package DESIGN_PATTERNS.Decorator;

public class main {
    public static void main(String[] args) {
        Smartphones smartphone = new Smartphone();
        smartphone = new IPhoneDecorator(smartphone);

        System.out.println("Cost: " + smartphone.getCost());
        System.out.println("Color: " + smartphone.getColor());
        System.out.println("Memory: " + smartphone.getmemeory() + " GB");
    }
}
