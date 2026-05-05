//Name: Michael Kenny
//Title: CSC Assignment #2 (CSC20 - Number Matching)
//Date: 2/11/2021
//Professor: Faroughi
//Description: Design and implement an application taht simulates the number matching game in which three numbers between 
//             1-9 inclusive are randomly generated and printed side by side. Your program examines the three generated numbers based on the following:
//             1. if the three generated numbers are matching, then the person will win 300. 2. if two of the three match, then the person will win 100.
//             3. if no matching numbers, the person wins nothing.
//             your program must keep track of the amount money the person has gained throughout playing the game and displays the total amount at the end.

import java.util.Scanner;
import java.util.Random;

class NumberMatching {

   public static void main(String [] args) {
      Random rand = new Random();
      Scanner in = new Scanner(System.in);
      String answer = "";
      while(!answer.equalsIgnoreCase("q")) { //as long as user does not enter q, game will continue to ask for a new player
         description();
         System.out.print("Please enter your name: ");
         String name = in.nextLine();
         System.out.println();
         System.out.println("Hello " + name + " lets start playing");
         System.out.println();
         play(rand); //starts game
         System.out.println("Hit enter to let another person play or enter Q to quit the program");
         answer = in.nextLine(); //if q or Q is entered, entire game stops
      
      }
      System.out.println("Goodbye!");
   
   }

   public static int getRandNum(Random rand) {
      int num = rand.nextInt(10); //gets random number from 1-9 inclusive
      return num;
   
   }
   
   public static int match(int n1, int n2, int n3) {
      if(n1 == n2 || n2 == n3 || n1 == n3) { //if 2 numbers equal, returns 2
         return 2;
      
      }else if(n1 == n2 && n2 == n3) { //if all numbers equal, return 3
         return 3;
      
      }else { //if all are unique, return 0
         return 0;
      
      }
   
   }
   
   public static void description() {
      System.out.println("********************************************************************************************");
      System.out.println("* Welcome to number matching game. I will generate three random numbers for you. If two    *");
      System.out.println("* of the numbers match you win 100, if you get three matching numbers you win 300 dollars  *");
      System.out.println("********************************************************************************************");
   
   }
   
   public static void play(Random rand) {
      Scanner in = new Scanner(System.in);
      int total = 0;
      String answer = "";
      int n1 = 0, n2 = 0, n3 = 0;
      while(!answer.equalsIgnoreCase("q")) { //while user does not enter q or Q, game will continue to generate new random numbers and total score accordingly
         n1 = getRandNum(rand);
         n2 = getRandNum(rand);
         n3 = getRandNum(rand);
         System.out.println(n1 + ", " + n2 + ", "  + n3);
         int match = match(n1, n2, n3);
         if(match == 2) {
            total = total + 100;
            System.out.println("You got two matches, you won 100 dollars");
         
         }else if(match == 3) {
            total = total + 300;
            System.out.println("You got three matches, you won 300 dollars");
         
         }else {
            System.out.println("You got no matches, you win nothing");
         
         }
         System.out.print("\nHit enter to continue or press q/Q to quit ");
         answer = in.nextLine();
      
      }
      
      System.out.println("\nTotal amount you won: " + total); //displays final total at end of game
      System.out.println("\n");
   
   }

}