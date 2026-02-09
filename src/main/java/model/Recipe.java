package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recipe {
    public final String name;
    private final List<String> ingredients = new ArrayList<>();
    private final Set<String> setOfIngredients;

    public Recipe(String[] info) {
        name = info[0];
        
        for (int i = 1; i < info.length; i++) {
            ingredients.add(info[i]);
        }
        setOfIngredients = new HashSet<>(ingredients);
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public boolean contains(String ingredient) {
        return setOfIngredients.contains(ingredient);
    }
}