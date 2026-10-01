package CoffeeMachine;

import java.util.Map;
import java.util.HashMap;

public class Inventory {
    private final Map<Ingredient, Integer> stock =new HashMap<>();

    public Inventory() {
        for (Ingredient ingredient : Ingredient.values()) {
            stock.put(ingredient, 0);
        }
    }

    public synchronized void add(Ingredient ingredient, int quantity) {
        stock.put(ingredient,stock.get(ingredient) + quantity);
    }

    public synchronized boolean canMake(Recipe recipe) {

        for (Map.Entry<Ingredient, Integer> entry :
                recipe.getIngredients().entrySet()) {

            int available = stock.get(entry.getKey());
            if (available < entry.getValue()) {
                return false;
            }
        }

        return true;
    }

    public synchronized void consume(Recipe recipe) {

        if (!canMake(recipe)) {
            throw new IllegalStateException(
                    "Insufficient ingredients"
            );
        }

        for (Map.Entry<Ingredient, Integer> entry :
                recipe.getIngredients().entrySet()) {

            Ingredient ingredient = entry.getKey();
            int required = entry.getValue();

            stock.put(
                    ingredient,
                    stock.get(ingredient) - required
            );
        }
    }

    public synchronized void displayStock() {

        System.out.println("Inventory:");
        for (Ingredient ingredient : Ingredient.values()) {
            System.out.println(
                    ingredient + " = " + stock.get(ingredient)
            );
        }
    }

    public synchronized boolean tryConsume(Recipe recipe) {

        if (!canMake(recipe)) {
            return false;
        }
    
        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
    
            Ingredient ingredient = entry.getKey();
            int required = entry.getValue();
            stock.put(ingredient,stock.get(ingredient) - required);
        }
        return true;
    }
}
