import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {

        // Initializations

        Scanner in = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        String id;
        String name;
        String desc;
        double cost;
        boolean ynConfirm = false;

        // Create data for each product

        do {

            id = SafeInput.getRegExString(in, "Enter the product's 6 digit ID", "\\d{6}");
            name = SafeInput.getNonZeroLenString(in, "Enter the product name");
            desc = SafeInput.getNonZeroLenString(in, "Enter the product description");
            cost = SafeInput.getDouble(in, "Enter the product cost");
            ynConfirm = SafeInput.getYNConfirm(in, "Do you have more entries to make?");
            Product P = new Product(name, desc, id, cost);
            products.add(P);

        } while(ynConfirm);

        // File Input for each person in the ArrayList

        File workingDirectory = new File(System.getProperty("user.dir")); // Project Directory
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt"); // Select input file

        try {

            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(Product rec : products) {

                writer.write(rec.toCSVDataRecord(), 0, rec.toCSVDataRecord().length()); // 0 represents the starting character on the write
                writer.newLine();

            }
            writer.close(); // closes the file and flushes buffer
            System.out.println("Data written to file");
        }
        catch (IOException e) {

            e.printStackTrace();

        }
    }
}