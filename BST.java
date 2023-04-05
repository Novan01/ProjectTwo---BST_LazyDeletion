import java.util.Scanner;
import java.io.*;

public class BST {
    public static String convertBooltoString(boolean status) {
        return String.valueOf(status); 
    }

    public static void main(String[] args) throws IOException {
        // 
         Scanner in = null;
         FileOutputStream out = null;

         //writer to output file
         PrintWriter pw = null;

         Scanner userInput = new Scanner(System.in);

         try {
            System.out.println("Enter input file name");
            in = new Scanner(userInput.nextLine());
            System.out.println("Enter output file name");
            out = new FileOutputStream(userInput.nextLine());

            pw = new PrintWriter(out);

            LazyBinarySearchTree tree = new LazyBinarySearchTree();
            int key = 0;

            while(in.hasNextLine()) {
                String process = in.nextLine().trim();
                if(process.indexOf("Insert") == 0) {
                    key = Integer.parseInt(process.substring(1));
                    try {
                        pw.println(convertBooltoString(tree.insert(key)));
                    } catch(IllegalArgumentException exception) {
                        pw.println(exception.getMessage());
                    }
                }
                else if(process.indexOf("Delete") == 0) {
                    key = Integer.parseInt(process.substring(1));
                    try {
                        pw.println(convertBooltoString(tree.delete(key)));
                    } catch(IllegalArgumentException exception) {
                        pw.println(exception.getMessage());
                    }
                }
                else if(process.indexOf("PrintTree") == 0) {
                    pw.println(tree);
                }
                else if(process.indexOf("FindMin") == 0) {
                    pw.println(tree.findMin());
                }
                else if(process.indexOf("FindMax") == 0) {
                    pw.println(tree.findMax());
                }
                else if(process.indexOf("Height") == 0) {
                    pw.println(tree.height(tree.root));
                }
                else if(process.indexOf("Size") == 0) {
                    pw.println(tree.size());
                }
                else if(process.indexOf("Contains") == 0) {
                    key = Integer.parseInt(process.substring(1));
                    try {
                        pw.println(convertBooltoString(tree.contains(key)));
                    } catch(IllegalArgumentException exception) {
                        pw.println(exception.getMessage());
                    }
                }  
                else {
                    pw.println("Error in Line: " + process);
                }
            }
            in.close();
            pw.close();
            userInput.close();
            System.out.println(out);
         }
         catch(FileNotFoundException nf) {
            System.out.println(nf.getMessage());
         } 
    }
}
