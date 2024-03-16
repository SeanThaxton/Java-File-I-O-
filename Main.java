import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;

/**
 * Main
 */
public class Main {

     public static void main(String[] args) {

        //Creates temp file to hold the reformated data, for the original.

        //Prompt user for the target file to be reformated
         System.out.println("Enter the path and the name, of  the file you want to reformat: ");
         Scanner theScan = new Scanner(System.in);
         String user = theScan.nextLine();
         File userFile = new File(user);

         if(userFile.exists() && !userFile.isDirectory()){
               System.out.println("File exist in a different Dictionary");
         }else{
               System.out.println("File does not exist");
         }
         theScan.close();

        //Parse each sentence of the file to be edit.
        BufferedReader reader = new BufferedReader(new FileReader(userFile));
        String txt = reader.readLine();
        reader.close();

         BufferedWriter writer = new BufferedWriter();

        
     }
}