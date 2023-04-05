import java.util.Scanner;
import java.io.*;

public class BST {
    public static String convertBooltoString(boolean status) {
        return String.valueOf(status); 
    }

    public static void main(String[] args) throws IOException {
        // 
         FileInputStream in = null;
         FileOutputStream out = null;

         //writer to output file
         PrintWriter pw = null;

         Scanner userInput = new Scanner(System.in);

         try {
            in = new FileInputStream(userInput.nextLine());
            out = new FileOutputStream(userInput.nextLine());
         }
         finally {
            
         }
    }
}
