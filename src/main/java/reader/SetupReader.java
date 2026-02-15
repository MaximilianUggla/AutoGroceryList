package reader;

import model.GroceryItem;
import model.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SetupReader {
    private static final String startPath = "src/main/resources/";

    public static Set<GroceryItem>[] readInCatalogue() throws FileNotFoundException, InterruptedException, TimeoutException {
        List<String> fileNames = new ArrayList<>();
        Scanner scanner = new Scanner(new File(startPath + "PATHS.txt"));
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            fileNames.add(name);
        }
        scanner.close();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Set<GroceryItem>[] catalogueMatrix = (Set<GroceryItem>[]) new HashSet[fileNames.size()];

        for (String fileName : fileNames) {
            executorService.submit(() -> {
                Scanner scan = null;
                try {scan = new Scanner(new File(startPath + fileName + ".txt"));} catch (FileNotFoundException e) {throw new RuntimeException(e);}

                Set<GroceryItem> items = new HashSet<>();
                while (scan.hasNextLine()) {
                    items.add(new GroceryItem(scan.nextLine(), fileName));
                }
                scan.close();
                catalogueMatrix[fileNames.indexOf(fileName)] = items;
            });
        }

        executorService.shutdown();
        if (executorService.awaitTermination(5, TimeUnit.MINUTES)) {
            return catalogueMatrix;
        }  else {
            throw new TimeoutException("Tasks timed out");
        }
    }
}
