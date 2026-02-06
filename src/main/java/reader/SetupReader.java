package reader;

import model.GroceryItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SetupReader {

    public static Map<String, List<GroceryItem>> setup() throws FileNotFoundException {
        Map<String, List<GroceryItem>> lists = new HashMap<>();
        String startPath = "src/main/resources/";
        String endPath = ".txt";
        List<String> fileNames = new ArrayList<>();
        Scanner scanner = new Scanner(new File(startPath + "PATHS.txt"));

        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            fileNames.add(in);
        }
        scanner.close();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (String fileName : fileNames) {
            executorService.submit(() -> {
                Scanner scan = null;
                try {scan = new Scanner(new File(startPath + fileName + endPath));} catch (FileNotFoundException e) {throw new RuntimeException(e);}

                List<GroceryItem> list = new ArrayList<>();
                while (scan.hasNextLine()) {
                    list.add(new GroceryItem(fileName, scan.nextLine()));
                }
                scan.close();
                lists.put(fileName, list);
            });
        }

        return lists;
    }
}
