package reader;

import model.Recipe;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Reader {

    public static List<Recipe> getAllRecipes() throws FileNotFoundException {
        List<String[]> unParsedRecipes = new ArrayList<>();
        List<Recipe> allRecipes = new ArrayList<>();

        File f = new File("src/main/resources/Recipies.txt");
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            unParsedRecipes.add(in.split(","));
        }
        
        scanner.close();

        for (String[] recipe : unParsedRecipes) {
            allRecipes.add(new Recipe(recipe));
        }
        return allRecipes;
    }

    public static String[] getFilters() {return new Scanner(System.in).nextLine().split(", ");}

    public static String getInput() {return new Scanner(System.in).nextLine().trim();}

    public static Set<String> getPriorityFilters() throws FileNotFoundException {
        Set<String> priorities = new HashSet<>();
        File f = new File("src/main/resources/Priority.txt");
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            priorities.add(scanner.nextLine());
        }
        scanner.close();
        return priorities;
    }

    public static Set<String> getPantryItems() {
        Set<String> pantryItems = new HashSet<>();
        File f = new File("src/main/resources/PantryItems.txt");
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                pantryItems.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {e.printStackTrace();}
        return pantryItems;
    }
}
