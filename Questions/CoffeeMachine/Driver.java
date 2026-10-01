package CoffeeMachine;

public class Driver {
    public static void main(String[] args) {
        CoffeeVendingMachine machine = new CoffeeVendingMachine();

        // Fill inventory
        machine.getInventory().add(Ingredient.COFFEE_BEANS,100);
        machine.getInventory().add(Ingredient.MILK,500);
        machine.getInventory().add(Ingredient.WATER,1000);

        machine.getInventory().add(Ingredient.SUGAR,100);

        // Display menu
        machine.displayMenu();

        // Choose payment method
        machine.setPaymentStrategy(new UPIPayment());

        // Select coffee
        machine.selectCoffee(CoffeeType.LATTE);

        // Pay
        machine.insertMoney(100);
    }
}

/*
how would u add tea, introduce beverage as an abstraction
"How would you support different sizes?" , we can have an enum Size {SMALL, MEDIUM, LARGE},
and have coffee + size, determine the price


*/