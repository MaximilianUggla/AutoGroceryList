package model;

import reader.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShoppingList {
    private Map<String, Integer> groceries = new HashMap<>();
    private Set<String> pantryItems = Reader.getPantryItems();
    private Map<String, Integer> usedPantryItems = new HashMap<>();
    
    public ShoppingList(Mealplan mp) {
        for (Recipe r : mp.getMeals()) {
            for (String str : r.getIngredients()) {
                if (!pantryItems.contains(str)) {
                    groceries.put(str, groceries.getOrDefault(str, 0) + 1);
                } else {
                    usedPantryItems.put(str, usedPantryItems.getOrDefault(str, 0) + 1);
                }
            }
        }
    }

    public void add(String product) {
        groceries.put(product, groceries.getOrDefault(product, 0) + 1);
    }

    public void remove(String product) {
        if (groceries.get(product) > 1) {
            groceries.put(product, groceries.get(product) - 1);
        } else {groceries.remove(product);}
    }

    public List<String> getGroceries() {
        List<String> items = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : groceries.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                items.add(entry.getKey());
            }
        }
        return items;
    }

    @Override
    public String toString() {
        String str = "Pantry Items Required:                         |                " + "ShoppingList:\n";
        str = str + "===============================================================================\n";

        Iterator<Map.Entry<String, Integer>> uPIIterator = usedPantryItems.entrySet().iterator();
        Iterator<Map.Entry<String, Integer>> gIterator = groceries.entrySet().iterator();

        for (int i = 0; i < Math.max(usedPantryItems.size(), groceries.size()); i++) {
            if (uPIIterator.hasNext()) {
                Map.Entry<String, Integer> uPIEntry = uPIIterator.next();
                String temp = "";

                if (uPIEntry.getValue() > 1) {
                    temp = uPIEntry.getKey();
                    int dif = 23 - temp.length();
                    for (int x = 0; x < dif; x++) {
                        temp = temp + " ";
                    }
                    temp = temp + "for " + uPIEntry.getValue() + " dishes";
                } else {
                    temp = uPIEntry.getKey();
                }

                int dif = 23 - temp.length() + 24;
                for (int x = 0; x < dif; x++) {
                    temp = temp + " ";
                }

                str = str + temp + "|                ";
            } else {str = str + "                                               |                ";}

            if (gIterator.hasNext()) {
                Map.Entry<String, Integer> gEntry = gIterator.next();

                if (gEntry.getValue() > 1) {
                    str = str + "(" + gEntry.getValue() + ") " + gEntry.getKey() + "\n";
                } else {
                    str = str + gEntry.getKey() + "\n";
                }
            } else {str = str + "\n";}
        }
        return str;
    }
}