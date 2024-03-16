import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path and the name of the file you want to reformat: ");
        String filePath = scanner.nextLine();
        File inputFile = new File(filePath);

        try {
            // Check if the input file exists and is not a directory
            if (!inputFile.exists() || inputFile.isDirectory()) {
                System.out.println("File does not exist or is a directory.");
                return;
            }

            // Create a temporary file
            File tempFile = createTempFile();

            // Reformat the input file and write the result to the temporary file
            reformatFile(inputFile, tempFile);

            // Replace the content of the input file with the content of the temporary file
            replaceFileContent(tempFile, inputFile);

            // Delete the temporary file
            tempFile.delete();

            System.out.println("File reformatted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Method to create a temporary file with a unique name
    private static File createTempFile() throws IOException {
        int count = 1;
        File tempFile;
        do {
            tempFile = new File("Temp" + count + ".txt");
            count++;
        } while (tempFile.exists());
        tempFile.createNewFile();
        return tempFile;
    }

    // Method to reformat the content of the input file and write it to the temporary file
    private static void reformatFile(File inputFile, File tempFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into sentences using period as delimiter
                String[] sentences = line.split("\\.\\s*");
                for (String sentence : sentences) {
                    // Write each sentence to the temporary file
                    writer.write(sentence.trim());
                    writer.newLine(); // Add newline after each sentence
                }
            }
        }
    }

    // Method to replace the content of the original file with the content of the temporary file
    private static void replaceFileContent(File source, File destination) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Write each line from the temporary file to the original file
                writer.write(line);
                writer.newLine(); // Preserve original newline characters
            }
        }
    }
}
