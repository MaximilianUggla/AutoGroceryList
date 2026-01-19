package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recipie {
    public final String name;
    private List<String> ingredients = new ArrayList<>();
    private Set<String> setOfIngredients;

    public Recipie(String[] info) {
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