package reader;

import model.GroceryItem;
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

    public static Set<GroceryItem>[] setup() throws FileNotFoundException, InterruptedException, TimeoutException {
        String startPath = "src/main/resources/";

        List<String> fileNames = new ArrayList<>();
        Scanner scanner = new Scanner(new File(startPath + "PATHS.txt"));
        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            fileNames.add(in);
        }
        scanner.close();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Set<GroceryItem>[] supplyMatrix = (Set<GroceryItem>[]) new HashSet[fileNames.size()];

        for (String fileName : fileNames) {
            executorService.submit(() -> {
                Scanner scan = null;
                try {scan = new Scanner(new File(startPath + fileName + ".txt"));} catch (FileNotFoundException e) {throw new RuntimeException(e);}

                Set<GroceryItem> items = new HashSet<>();
                while (scan.hasNextLine()) {
                    items.add(new GroceryItem(scan.nextLine(), fileName));
                }
                scan.close();
                supplyMatrix[fileNames.indexOf(fileName)] = items;
            });
        }

        executorService.shutdown();
        if (executorService.awaitTermination(1, TimeUnit.HOURS)) {
            return supplyMatrix;
        }  else {
            throw new TimeoutException("Tasks timed out");
        }
    }
}
