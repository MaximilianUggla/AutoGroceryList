package reader;

import model.Recipie;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Reader {
    private static final Scanner terminalScanner = new Scanner(System.in, "Cp850");

    public static List<Recipie> getAllRecipies() throws FileNotFoundException {
        List<String[]> unParsedRecipes = new ArrayList<>();
        List<Recipie> allRecipes = new ArrayList<>();

        File f = new File("demo/src/main/resources/Recipies.txt");
        Scanner scan = new Scanner(f);
        while (scan.hasNextLine()) {
            String in = scan.nextLine();
            unParsedRecipes.add(in.split(","));
        }
        
        scan.close();

        for (String[] recipe : unParsedRecipes) {
            allRecipes.add(new Recipie(recipe));
        }
        return allRecipes;
    }

    public static String[] getFilters() {
        return terminalScanner.nextLine().split(", ");
    }

    public static String getInput() {
        return terminalScanner.nextLine().trim();
    }

    public static Set<String> getPriorityFilters() throws FileNotFoundException {
        Set<String> priorities = new HashSet<>();
        File f = new File("demo/src/main/resources/Priority.txt");
        Scanner scan = new Scanner(f);
        while (scan.hasNextLine()) {
            priorities.add(scan.nextLine());
        }
        scan.close();
        return priorities;
    }

    public static Set<String> getPantryItems() {
        Set<String> pantryItems = new HashSet<>();
        File f = new File("demo/src/main/resources/PantryItems.txt");
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                pantryItems.add(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException e) {e.printStackTrace();}
        return pantryItems;
    }

    public static void close() {
        terminalScanner.close();
    }
}
