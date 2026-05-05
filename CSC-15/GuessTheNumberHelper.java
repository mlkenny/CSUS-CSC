import java.util.Random;
import java.util.Scanner;

class GuessTheNumberHelper {
   
   public static int TRY_COUNT = 0; //global try counter variable
   public static boolean GAME_WON = false; //boolean to check if game is won
   public static int SECRET_NUM; //global secret number variable
   public static int user_input; //global user input from scanner variable
   public static String user_history = ""; //global user history variable
   
   public static void playGame() { //main algorithm, generates random number every time a new game is started, until game is won user is prompted and input is tested
      generateRandomNum();
      while (GAME_WON == false) {
         userPrompt();
         testUserInput(user_input);
         
      }
   
   }
   
   public static void userPrompt() { //prints basic prompt as well as constructs a scanner to accept user input
      Scanner in = new Scanner(System.in);
      System.out.print("Please Enter an Integer between 1 and 100: ");
      try {
         user_input = in.nextInt();
         if(user_input == (int)user_input) {
            userInputValidation(user_input); //calls validation funtion
         
         }
      }catch(Exception e) {
         System.out.println("That's not a number jack-ass");
         userPrompt();
      
      }
      
   }
   
   public static void generateRandomNum() { //generates a random number from 1-100
      Random rand = new Random();
      SECRET_NUM = rand.nextInt(101);
      
   }
   
   public static void userInputValidation(int user_input) { //checks if user input is within 1-100, if outside bounds then returns invalid error message and restarts testing
      if (user_input > 100 || user_input < 1) {
         System.out.println("You have entered an invalid number. Please choose a number between 1-100. "); //invalid error message
         testUserInput(user_input);
         
      }else {
         TRY_COUNT += 1; //user input is valid, increments try counter
         user_history = user_history + user_input + "\n"; //appends user input to user history
         
      }
      
   }
   
   public static void testUserInput(int user_input) { //evaluates valid user input against secret number
      if (user_input > SECRET_NUM) {
         System.out.println("Your Number is Too High");
         
      }else if (user_input < SECRET_NUM) {
         System.out.println("Your Number is Too Low");
         
      }else { //if user input == secret num then game will be won; prints winning message, prints final try count, prints user history, calls replay function
         GAME_WON = true;
         System.out.println("Congratulations!! You won!!");
         System.out.println("You had " + TRY_COUNT + " Tries");
         System.out.println("Here is the history of your attempts: ");
         System.out.println(user_history);
         replayMessage();
         
      }
      
   }
   
   public static void replayMessage() { //uses scanner to take in user input, if user input is yes then restart game otherwise exit program
      Scanner replay = new Scanner(System.in);
      System.out.print("Would you like to play again? Enter Y or N: ");
      String answer = replay.next();
      answer = answer.toUpperCase();
      if (answer.equalsIgnoreCase("Y")) { //if user input is yes then: game will not be won, try count resets, user history resets, game restarts 
         GAME_WON = false;
         TRY_COUNT = 0;
         user_history = "";
         playGame();
         
      }else {
         System.exit(0);
         
      }
   
   }
   
}