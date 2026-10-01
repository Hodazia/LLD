package CoffeeMachine;

import java.util.Map;
import java.util.HashMap;

public class CoffeeVendingMachine {
    private final Inventory inventory;
    private final Map<CoffeeType, Coffee> menu = new HashMap<>();
    private PaymentStrategy paymentStrategy;
    private MachineState state;
    private Coffee selectedCoffee;
    private double paidAmount;

    public CoffeeVendingMachine() {

        inventory = new Inventory();
        state = new IdleState();
        initializeMenu();
    }

    private void initializeMenu() {

        Recipe espressoRecipe =
                new Recipe(Map.of(
                        Ingredient.COFFEE_BEANS, 10,
                        Ingredient.WATER, 50
                ));

        Recipe latteRecipe =
                new Recipe(Map.of(
                        Ingredient.COFFEE_BEANS, 10,
                        Ingredient.MILK, 100,
                        Ingredient.WATER, 50
                ));

        Recipe cappuccinoRecipe =
                new Recipe(Map.of(
                        Ingredient.COFFEE_BEANS, 10,
                        Ingredient.MILK, 70,
                        Ingredient.WATER, 50
                ));

        menu.put(
                CoffeeType.ESPRESSO,
                new Coffee(
                        CoffeeType.ESPRESSO,
                        50,
                        espressoRecipe
                )
        );

        menu.put(
                CoffeeType.LATTE,
                new Coffee(
                        CoffeeType.LATTE,
                        80,
                        latteRecipe
                )
        );

        menu.put(
                CoffeeType.CAPPUCCINO,
                new Coffee(
                        CoffeeType.CAPPUCCINO,
                        70,
                        cappuccinoRecipe
                )
        );
    }
    public void displayMenu() {

        System.out.println("\n--- MENU ---");
        for (Coffee coffee : menu.values()) {
            System.out.println(coffee);
        }
    }
    public void selectCoffee(CoffeeType type) {

        Coffee coffee = menu.get(type);

        if (coffee == null) {
            System.out.println(
                    "Coffee not available."
            );
            return;
        }

        state.selectCoffee(
                coffee,
                this
        );
    }

    public void insertMoney(double amount) {

        if (amount <= 0) {
            System.out.println(
                    "Invalid amount."
            );
            return;
        }

        state.insertMoney(
                amount,
                this
        );
    }

    public synchronized void dispense() {

        state.dispense(this);
    }

    public void cancel() {

        state.cancel(this);
    }

    public void setPaymentStrategy(
            PaymentStrategy paymentStrategy) {

        this.paymentStrategy =
                paymentStrategy;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Coffee getSelectedCoffee() {
        return selectedCoffee;
    }

    public void setSelectedCoffee(Coffee coffee) {

        this.selectedCoffee = coffee;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void addPayment(double amount) {
        paidAmount += amount;
    }

    public void resetPayment() {
        paidAmount = 0;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public void reset() {

        selectedCoffee = null;
        paidAmount = 0;
    }

}
