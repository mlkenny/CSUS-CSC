import java.io.File;
import java.util.Scanner;

public class QueueTest {
    public static void main(String [] args) {
        // INITIALIZING QUEUE
        // Initialize a main queue and a digits var to track how large keys are (used in radix sort below).
        Queue mainQueue = new Queue();
        int keySize = 0;

        // ABOUT
        System.out.println(mainQueue.About());

        // OPEN FILE
        // Opens test file and constructs queue with all keys and values.
        File input = new File("E:\\VSCode\\CSC-130\\LinkedList\\halloween calories.txt");
        System.out.println("File name: " + input.getName());
        try { // Try catch block to catch file not found exception.
            Scanner reader = new Scanner(input);
            keySize = reader.nextInt();
            while(reader.hasNextLine() || reader.hasNextInt()) {
                int key = reader.nextInt();
                reader.nextLine(); // Necessary; nextInt does not skip lines, must do so manually or else blank string is returned.
                String value = reader.nextLine();
                if(!value.equalsIgnoreCase("END")) {
                    // Creates new field with key and value and enqueues to queue.
                    Field newField = new Field(key, value.trim());
                    mainQueue.Enqueue(newField);
                }else {
                    break;
                }
            }
            reader.close(); // Close reader to avoid loitering.
        }catch(Exception e) {
            System.out.println("An error occurred while opening/reading the file.");
        }

        // FIRST TESTS
        // Print out mainQueue forwards and in reverse.
        System.out.println("\nMain Queue: \n" + mainQueue.ToString());
        System.out.println("\nMain Queue Reversed: \n" + mainQueue.ToStringReverse());

        // SETUP BUCKETS FOR RADIX SORT
        // Create 10 buckets (base 10 numbers).
        Queue[] buckets = new Queue[10];
        for(int i = 0; i < buckets.length; i ++) {
            buckets[i] = new Queue();
        }
        
        // This tracks place value for the key.
        int placeValue = 1; // Decided to go with this instead of turning the key into a string, makes logical sense to keep it an int to me.
        
        // RADIX SORT
        System.out.println("\nCommencing Radix Sort on Main Queue...\n");
        // Start of Radix Sort Logic
        for(int j = 0; j < keySize; j++) {
            // Fill buckets by emptying from the mainQueue.
            while (!mainQueue.IsEmpty()) {
                Field currentField = mainQueue.Dequeue();
                buckets[(currentField.key / placeValue) % 10].Enqueue(currentField);
            }
            // Empty all buckets back into mainQueue.
            for (int i = 0; i < buckets.length; i++) {
                while (!buckets[i].IsEmpty()) {
                    mainQueue.Enqueue(buckets[i].Dequeue());
                }
            }
            placeValue *= 10; // Increment placeValue by 10, this gets called keySize times.
        }

        // SECOND TESTS ON SORTED QUEUE
        // Print out the newly radix sorted queue forwards and in reverse.
        System.out.println("Sorted Main Queue: \n" + mainQueue.ToString());
        System.out.println("\nSorted Main Queue Reversed: \n" + mainQueue.ToStringReverse());
    }
}