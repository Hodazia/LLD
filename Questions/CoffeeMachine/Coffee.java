package CoffeeMachine;

public class Coffee {
    // coffeetype, receipe, price, 
    private final CoffeeType type;
    private final double price;
    private final Recipe recipe;

    public Coffee(CoffeeType type, double price, Recipe recipe) {
        this.type = type;
        this.price = price;
        this.recipe = recipe;
    }

    public CoffeeType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return type + " - ₹" + price;
    }
}
