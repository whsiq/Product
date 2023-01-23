import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {

        // Initializations

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec;
        ArrayList<String> records = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        int length = 4;
        String id;
        String name;
        String desc;
        double cost;

        // Processing

        try {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int line = 0;

                while (reader.ready()) {
                    rec = reader.readLine();
                    records.add(rec);
                    line++;
                }

                reader.close();
                System.out.println("\nFile read successfully");

                String[] fields;

                System.out.print("ID#   | Product Name |      Description       |  Cost");
                System.out.print("\n========================================================");
                for (String l : records) {

                    fields = l.split(",");

                    if (fields.length == length) {
                        id = fields[0].trim();
                        name = fields[1].trim();
                        desc = fields[2].trim();
                        cost = Double.parseDouble(fields[3].trim());
                        Product P = new Product(name, desc, id, cost);
                        products.add(P);
                    }
                    else {
                        System.out.println("File may be corrupt: ");
                        System.out.println(l);
                    }
                }
                for (Product i : products) {
                    System.out.printf("\n%-8s%-15s%-25s%6.2f", i.getID(), i.getName(), i.getDescription(), i.getCost());
                }

            }

            else {

                System.out.println("No file chosen");
                System.out.println("Please run the program again.");
                System.exit(0);

            }
        }

        catch(FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        catch(IOException e) {
            e.printStackTrace();
        }

    }
}