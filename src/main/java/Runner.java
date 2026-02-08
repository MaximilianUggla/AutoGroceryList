import java.io.FileNotFoundException;
import java.util.concurrent.TimeoutException;
import SearchTree.TrieTree;
import model.GroceryItem;
import reader.SetupReader;
import java.util.Set;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, TimeoutException {
        Set<GroceryItem>[] supplyMatrix = SetupReader.setup();
        TrieTree searchTree = new TrieTree();


        for (Set<GroceryItem> category : supplyMatrix) {
            for (GroceryItem item : category) {
                searchTree.insert(item);
            }
        }
        System.out.println(searchTree.startsWith("kalkon"));
    }

    public static <T> void print(T object) {System.out.println(object);}
}