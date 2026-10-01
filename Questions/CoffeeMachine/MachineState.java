package CoffeeMachine;

public interface MachineState {
    void selectCoffee(Coffee coffee, CoffeeVendingMachine machine);
    void insertMoney(double amount,CoffeeVendingMachine machine);
    void dispense(CoffeeVendingMachine machine);
    void cancel(CoffeeVendingMachine machine);
}

class IdleState implements MachineState {
    // idle state, when the user is selecting a coffee

    @Override 
    public void selectCoffee(Coffee coffee,CoffeeVendingMachine machine)
    {
        // check if we have sufficient stock for the selected coffee,
        // from the machine get the inventory and check if we can make coffee,  like canMake(recipe) , get the recipe from the coffee
        if(!machine.getInventory().canMake(coffee.getRecipe())){
            System.out.println("Insufficient stock for the selected coffee");
            return;
        }
        // if we can make the coffee, set the state to brewing
        machine.setSelectedCoffee(coffee);
        System.out.println("Selected: " + coffee);
        machine.setState(new PaymentState());
    }

    @Override
    public void insertMoney(double amount, CoffeeVendingMachine machine) {

        System.out.println("Please select a coffee first.");
    }

    @Override
    public void dispense(CoffeeVendingMachine machine) {

        System.out.println("Please select a coffee first.");
    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {

        System.out.println("Nothing to cancel.");
    }
}

class PaymentState implements MachineState {
    // payment state when the user is inserting money 

    @Override 
    public void selectCoffee(Coffee coffee,CoffeeVendingMachine machine)
    {
        System.out.println("Coffee already selected.");
    }

    @Override
    public void insertMoney(double amount, CoffeeVendingMachine machine) {

        // get the coffee first , machine will also have the payment strategy, we can use that to pay
        Coffee coffee = machine.getSelectedCoffee();
        if(!machine.getPaymentStrategy().pay(amount)){
            System.out.println("Payment failed.");
            return ;
        }

        machine.addPayment(amount);

        double paid = machine.getPaidAmount();
        double price = coffee.getPrice();

        if (paid >= price) {

            double change = paid - price;
            if (change > 0) {
                System.out.println(
                        "Change: ₹" + change
                );
            }
            machine.setState(new DispensingState());
        machine.dispense();
    } 
    else {
        System.out.println("Remaining amount: ₹" + (price - paid));
    }
    }

    @Override
    public void dispense(CoffeeVendingMachine machine) {

        System.out.println("Please complete payment first.");
    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {

        double amount = machine.getPaidAmount();

        if (amount > 0) {
            machine.getPaymentStrategy().refund(amount);
            machine.resetPayment();
        }

        machine.setState(new IdleState());
    }
}

class DispensingState implements MachineState {

    @Override
    public void selectCoffee(Coffee coffee,CoffeeVendingMachine machine) {
        System.out.println("Currently dispensing coffee.");
    }

    @Override
    public void insertMoney(double amount,CoffeeVendingMachine machine) {

        System.out.println("Please wait.");
    }

    @Override
    public void dispense(CoffeeVendingMachine machine) {

        Coffee coffee = machine.getSelectedCoffee();
        machine.getInventory().consume(coffee.getRecipe());
        System.out.println("Preparing " + coffee.getType());

        System.out.println("☕ " + coffee.getType()+ " ready!");

        machine.reset();
        machine.setState(new IdleState());
    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {

        System.out.println("Cannot cancel while dispensing.");
    }
}