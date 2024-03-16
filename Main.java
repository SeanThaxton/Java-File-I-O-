import java.util.Scanner;
import java.io.BufferedWriter;
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
         theScan.close();

        //Parse each sentence of the file to be edit.
         BufferedReader reader = new BufferedReader();

         BufferedWriter writer = new BufferedWriter();

        
     }
}