import model.Mealplan;
import model.Recipie;
import model.ShoppingList;
import reader.Reader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Recipie> allRecipes = Reader.getAllRecipies();
        Set<String> priorities = Reader.getPriorityFilters();

        int length = retrieveLength();
        
        printInformation();
        String[] filters = Reader.getFilters();

        Mealplan mp = new Mealplan(length, filters, allRecipes, priorities);
        ShoppingList sl = new ShoppingList(mp);

        boolean loop = true;
        while (loop) {
            System.out.println("\n" + mp + "\n" + sl);

            System.out.println("You can edit the shopping list:\n1. Add 1 item\n2. Remove 1 item\n3. Send list to OurGroceries\n4. Quit");
            String option = Reader.getInput();
            if (option.equals("1")) {
                System.out.println("What would you like to add:");
                String product = Reader.getInput();
                sl.add(product);
            } if (option.equals("2")) {
                System.out.println("What would you like to remove:");
                String product = Reader.getInput();
                sl.remove(product);
            } if (option.equals("3")){
                sendList(sl);
            }if (option.equals("4")) {loop = false;}            
        }
        Reader.close();
    }

    private static void sendList(ShoppingList sl) throws IOException, InterruptedException {
        // OurGroceriesClient client = new OurGroceriesClient();
        //client.send(sl);
    }

    private static int retrieveLength() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        int length = 7;

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Specify how many days the meal plan should cover.");
        System.out.println("Blank will be interpreted as 7 days: ");
        String input = Reader.getInput();
        
        if (!input.isBlank()) {length = Integer.parseInt(input);}
        
        return length;
    }

    private static void printInformation() {
        System.out.println("Write some grocery items that the meal plan will be based on.");
        System.out.println("Use the following format: grocery, grocery, ...");
        System.out.println("Blank will be interpreted as no preference: ");
    }
}