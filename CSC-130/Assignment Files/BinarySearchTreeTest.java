import java.io.File;
import java.util.Scanner;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        // INITIALIZE BINARY SEARCH TREE
        BinarySearchTree binaryTree = new BinarySearchTree();

        // ABOUT
        System.out.println(binaryTree.About());

        // OPEN FILE
        // Opens test file and constructs queue with all keys and values.
        File input = new File("E:\\VSCode\\CSC-130\\Assignment Files\\california.txt");
        System.out.println("File name: " + input.getName());
        // Open scanner and add values/keys from file to binaryTree.
        try {
            Scanner reader = new Scanner(input);
            while(reader.hasNextLine()) {
                int key = reader.nextInt();
                reader.nextLine();
                String value = reader.nextLine();
                if(!value.equalsIgnoreCase("END")) {
                    binaryTree.add(key, value); // Builds the tree.
                }else {
                    break;
                }
            }
            reader.close();
        }catch (Exception e) {
            System.out.println("An error occurred while opening/reading the file.");
        }

        // PRINT TESTS
        System.out.println("\nSTARTING TESTS" + "\n_______________________");
        // TESTING FINDING EXISTING NODE, MANUAL INPUT REQUIRED IF FILE CHANGES
        System.out.println("\nTesting find on key = 1869: " + binaryTree.find(1869));
        System.out.println("\nTesting Sorted List: \n" + binaryTree.toSortedList());
        System.out.println("\nTesting toTree: \n" + binaryTree.toTree());
        // TESTING ADDING NEW NODE, MANUAL INPUT REQUIRED IF FILE CHANGES
        System.out.println("Testing add on: " + "key: 1985, value: Sacramento Kings Established"); // Choose new node key and value.
        binaryTree.add(1985, "Sacramento Kings Established"); // Add chosen node to the BST.
        // TESTING FINDING NEW NODE, MANUAL INPUT REQUIRED IF FILE CHANGES
        System.out.println("Testing find on key = 1985: " + binaryTree.find(1985));
        System.out.println("\nTesting Sorted List with added node: \n" + binaryTree.toSortedList());
        System.out.println("\nTesting toTree with added node: \n" + binaryTree.toTree());
    }
}