import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Prompt user for the target file to be reformatted
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path and the name of the file you want to reformat: ");
        String filePath = scanner.nextLine();
        File userFile = new File(filePath);

        try {
            if (!userFile.exists() || userFile.isDirectory()) {
                System.out.println("File does not exist or is a directory.");
                return;
            }

            // Create a temporary file
            File tempFile = createTempFile();

            // Parse each sentence of the file to be edited
            BufferedReader reader = new BufferedReader(new FileReader(userFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] sentences = line.split("\\.\\s*");
                for (String sentence : sentences) {
                    writer.write(sentence.trim());
                    writer.newLine();
                }
            }

            // Close resources
            reader.close();
            writer.close();

            // Copy contents of temporary file back into the source file
            copyFileContents(tempFile, userFile);

            // Delete temporary file
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

    // Method to copy contents of one file to another
    private static void copyFileContents(File source, File destination) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(source));
        BufferedWriter writer = new BufferedWriter(new FileWriter(destination));

        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}
