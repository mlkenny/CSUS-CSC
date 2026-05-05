import java.util.*;
import java.io.*;

class FindGoldHelper {
   
   public static void processData() {
      Scanner console = new Scanner(System.in); //creates new scanner for user input
      processLine(getFileName(console), getSearchWord(console)); //calls the processLine function which allows user to input file choice, 
                                                                 //search word choice, and parses file all at once
   
   }

   public static int searchWordCount = 0; //counter that keeps track of how many times the given search word is found
   
   public static String getFileName(Scanner console) { //method gets file name from user and returns said file name
      System.out.print("What is your file name?: ");
      String fileName = console.nextLine();
      return fileName;
   
   }
   
   public static String getSearchWord(Scanner console) { //method gets search word from user and returns said search word
      System.out.print("What is your search word?: ");
      String searchWord = console.next();
      System.out.println(); //prints gap for aesthetic purposes
      return searchWord;
   
   }
   
   public static void processLine(String fileName, String searchWord) { //method tries opening file and while file has a next line the line is scanned to find
                                                                        // all occurences of the search word within that single line. counter incremented per search word found
      try {
         Scanner inFile = new Scanner(new File(fileName));
         while (inFile.hasNextLine()) {
            String line = inFile.nextLine();
            System.out.println(line);
            line = line.toLowerCase();
            int index = line.indexOf(searchWord);
               while (index >= 0) {
                  searchWordCount++;
                  index = line.indexOf(searchWord, index + searchWord.length());
               
               }  
               
         }
         
         displayFinalMessage(fileName, searchWord); //displays final message
           
      }catch (Exception e) { //if file mismatch exception is thrown, error message displays telling user to retry
         Scanner console = new Scanner(System.in);
         System.out.print("File cannot be found, would you like to try again? Y/N: ");
         String response = console.next();
         if (response.toLowerCase().equals("y")) { //depending on user response, will either restart program by resetting search word counter and recalling process data method
                                                   //or will exit program completely
            System.out.println();
            searchWordCount = 0;
            processData();
      
         }else {
            System.exit(0);
      
         }
   
      }
      
   }
   
   public static void displayFinalMessage(String fileName, String searchWord) { //displays final message of search word count of search word in file name
                                                                                //uses a scanner to detect user input for whether user would like to scan another file, if not program closes
      Scanner userResp = new Scanner(System.in);
      System.out.println();
      System.out.println("There were " + searchWordCount + " occurrences of " + searchWord + " within file " + fileName);
      System.out.println();
      System.out.print("If you would like to scan another file please enter Y/N: ");
      String response = userResp.next();
      if (response.toLowerCase().equals("y")) {
         System.out.println();
         searchWordCount = 0;
         processData();
      
      }else {
         System.exit(0);
      
      }
      
   
   }
   
}