import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import SearchTree.TrieTree;
import model.GroceryItem;
import model.Mealplan;
import model.Recipe;
import model.ShoppingList;
import reader.Reader;
import reader.SetupReader;
import java.util.Set;

public class Runner {
    private static final TrieTree searchTree = new TrieTree();
    private static final List<Recipe> allRecipes = new ArrayList<>();
    private static final Thread t1 = new Thread(() -> {
        Scanner scanner = null;
        try {scanner = new Scanner(new File("src/main/resources/Recipies.txt"));} catch (FileNotFoundException e) {throw new RuntimeException(e);}

        while (scanner.hasNextLine()) {
            String[] recipe = scanner.nextLine().split(",");
            allRecipes.add(new Recipe(recipe));
        }
        scanner.close();
    });
    private static final Thread t2 = new Thread(() -> {

    });


    public static void main(String[] args) throws FileNotFoundException, InterruptedException, TimeoutException {
        t1.start();

        Set<GroceryItem>[] catalogueMatrix = SetupReader.readInCatalogue();

        for (Set<GroceryItem> category : catalogueMatrix) {
            for (GroceryItem item : category) {
                searchTree.insert(item);
            }
        }

        Set<GroceryItem> priorities = catalogueMatrix[0];

        /*int length = getLength();
        String[] filter = getFilters();
        Mealplan mp = new Mealplan(length, filters, allRecipes, priorities);
        ShoppingList sl = new ShoppingList(mp);*/


        t1.join();


    }

    public static <T> void print(T object) {System.out.println(object);}
}