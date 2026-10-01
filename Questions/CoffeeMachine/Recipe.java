package CoffeeMachine;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;


public class Recipe {
    private final Map<Ingredient, Integer> ingredients;
    public Recipe(Map<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public Map<Ingredient, Integer> getIngredients() {
        return Collections.unmodifiableMap(ingredients);
    }

    
}
