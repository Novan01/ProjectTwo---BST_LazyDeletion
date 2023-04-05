import java.util.Scanner;
import java.io.*;

public class BST {
    public static String convertBooltoString(boolean status) {
        return String.valueOf(status); 
    }

    public static void main(String[] args) throws IOException {
         //input and output files
         Scanner in = null;
         FileOutputStream out = null;
         File file = null;

         //writer to output file
         PrintWriter pw = null;

         //user input scanner for file names
         Scanner userInput = new Scanner(System.in);

         try {
            //get input and output file names to create in local folder
            System.out.println("Enter input file name");
            file = new File(userInput.nextLine());
            in = new Scanner(file);
            System.out.println("Enter output file name");
            out = new FileOutputStream(userInput.nextLine());

            //create writer to write to output file
            pw = new PrintWriter(out);

            LazyBinarySearchTree tree = new LazyBinarySearchTree();
            int key = 0;

            //begin to read the file 
            while(in.hasNext()) {
                String process = in.nextLine().trim();
                //check for Inesrt
                if(process.indexOf("Insert:") == 0) {
                    String insert = "Insert:";
                    key = Integer.parseInt(process.substring(process.indexOf(insert) + insert.length()).trim());
                    try {
                        pw.println(convertBooltoString(tree.insert(key)));
                    } catch(IllegalArgumentException exception) {
                        pw.println(exception.getMessage());
                    }
                }
                //Check for Delete
                else if(process.indexOf("Delete") == 0) {
                    String delete = "Delete:";
                    key = Integer.parseInt(process.substring(process.indexOf(delete) + delete.length()).trim());
                    try {
                        pw.println(convertBooltoString(tree.delete(key)));
                    } catch(IllegalArgumentException exception) {
                        pw.println(exception.getMessage());
                    }
                }
                //Check for Print
                else if(process.indexOf("PrintTree") == 0) {
                    pw.println(tree);
                }
                //Check for FindMin
                else if(process.indexOf("FindMin") == 0) {
                    pw.println(tree.findMin());
                }
                //Check for FindMax
                else if(process.indexOf("FindMax") == 0) {
                    pw.println(tree.findMax());
                }
                //Check for Height
                else if(process.indexOf("Height") == 0) {
                    pw.println(tree.height(tree.root));
                }
                //Check for Size
                else if(process.indexOf("Size") == 0) {
                    pw.println(tree.size());
                }
                //Check for Contains
                else if(process.indexOf("Contains:") == 0) {
                    String contains = "Contains:";
                    key = Integer.parseInt(process.substring(process.indexOf(contains) + contains.length()).trim());
                    try {
                        pw.println(convertBooltoString(tree.contains(key)));
                    } catch(IllegalArgumentException exception) {
                        pw.println(exception.getMessage());
                    }
                }  
                //If no function found return error
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
