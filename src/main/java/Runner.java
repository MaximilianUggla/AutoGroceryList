import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import SearchTree.TrieTree;
import model.GroceryItem;
import model.Recipe;
import reader.SetupReader;
import java.util.Set;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, TimeoutException {
        final List<Recipe> allRecipes = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            Scanner scanner = null;
            try {scanner = new Scanner(new File("src/main/resources/Recipies.txt"));} catch (FileNotFoundException e) {throw new RuntimeException(e);}

            while (scanner.hasNextLine()) {
                String[] recipe = scanner.nextLine().split(",");
                allRecipes.add(new Recipe(recipe));
            }
            scanner.close();
        });
        t1.start();

        Set<GroceryItem>[] catalogueMatrix = SetupReader.readInCatalogue();
        TrieTree searchTree = new TrieTree();

        for (Set<GroceryItem> category : catalogueMatrix) {
            for (GroceryItem item : category) {
                searchTree.insert(item);
            }
        }

        t1.join();


    }

    public static <T> void print(T object) {System.out.println(object);}
}