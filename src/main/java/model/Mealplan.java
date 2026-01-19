package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Mealplan {
    private List<Recipie> mealplan = new ArrayList<>();
    private Random rand = new Random();
    private int nbrOfMeals;
    private List<Recipie> allRecipies;
    private List<String> priorities;

    public Mealplan(int nbrOfMeals, String[] filters, List<Recipie> allRecipes, Set<String> allPriorities) {
        this.nbrOfMeals = nbrOfMeals;
        this.allRecipies = allRecipes;
        pickReleventPriorities(allPriorities, filters);
        generateMealplan();
    }

    public List<Recipie> getMeals() {
        mealplan.sort((r1, r2) -> r1.name.compareTo(r2.name));
        return new ArrayList<>(mealplan);
    }

    private void generateMealplan() {
        if (!priorities.isEmpty()) {
            addBasedOnPriority();
        }
        adjustMealpanToSize();
    }

    private void adjustMealpanToSize() {        
        if (mealplan.size() < nbrOfMeals) {
            int nbrToAdd = nbrOfMeals - mealplan.size();
            for (int i = 0; i < nbrToAdd; i++) {
                mealplan.add(randomMeal());
            }

        } else if (mealplan.size() > nbrOfMeals) {
            int nbrToRemove = mealplan.size() - nbrOfMeals;
            for (int i = 0; i < nbrToRemove; i++) {
                mealplan.remove(mealplan.size() - 1);
            }
        }
    }

    private void addBasedOnPriority() {
        for (String p : priorities) {
            List<Recipie> options = new ArrayList<>();
            for (Recipie r : allRecipies) {
                if (r.contains(p)) {
                    options.add(r);
                }
            }
            int size = options.size();
            for (int i = 0; i < (size > 2 ? 2 : size); i++) {
                mealplan.add(options.get(rand.nextInt(0, size)));
            }
        }
    }

    private Recipie randomMeal() {
        int randomIndex = rand.nextInt(0, allRecipies.size());
        return allRecipies.get(randomIndex);
    }

    private void pickReleventPriorities(Set<String> allPriorities, String[] filters) {
        List<String> priorities = new ArrayList<>();
        
        for (String f : filters) {
            if (allPriorities.contains(f)) {
                priorities.add(f);
            }
        }
        this.priorities = priorities;
    }

    @Override
    public String toString() {
        String str = "Meal plan for " + nbrOfMeals + " days:\n";
        for (int i = 0; i < mealplan.size(); i++) {
            str = str + (i+1) + ". " + mealplan.get(i).name + "\n";
        }
        return str;
    }
}