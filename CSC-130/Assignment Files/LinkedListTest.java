import java.io.File;
import java.util.Scanner;

public class LinkedListTest {
    public static void main(String[] args) throws Exception {
        // Initialize linked list and file/scanner objects.
        LinkedList list = new LinkedList();
        File input = new File("E:\\VSCode\\CSC-130\\LinkedList\\pokemon.txt");
        Scanner reader = new Scanner(input);
        // While file has another line, add line to tail of linked list.
        while(reader.hasNextLine()) {
            Field newField = new Field(0, reader.nextLine().trim());
            if(!newField.value.equalsIgnoreCase("END")) {
                list.AddToTail(newField);
            }
        }
        reader.close();
        // Print tests for ToString and ToStringReverse.
        System.out.println("Testing ToString...\n" + list.ToString() + "\n");
        System.out.println("Testing ToStringReverse...\n" + list.ToStringReverse() + "\n");
        // Remove 2 items from tail.
        for(int i = 0; i < 2; i++) {
            list.RemoveTail();
        }
        // Remove 2 items from head.
        for(int i = 0; i < 2; i++) {
            list.RemoveHead();
        }
        // Print tests for RemoveTail and RemoveHead.
        System.out.println("Testing 2 element RemoveTail and RemoveHead...\n" + list.ToString() + "\n");
    }
}