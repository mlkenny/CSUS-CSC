//Name: Michael Kenny
//Title: Programming Assignment #7 (CSC15 - Trivia Game)
//Date: 11/28/2020
//Description: Write a program where a game is created in which the user is asked trivial questions 
//             and must give a response. Score is tallied and displayed in the end. Each question is worth a different amount of points

import java.util.Scanner;

class TriviaGame {

   public static int SIZE = 5;

   public static void main(String [] args) {
      run();
   
   }
   
   public static void run() {
      String[] questions = new String[SIZE]; //initilize all arrays and arrays have size of SIZE
      String[] answers = new String[SIZE];
      int[] points = new int[SIZE];
      initilize(questions, answers, points);
      play(questions, answers, points);
      
   }
   
   public static void initilize(String[] questions, String[] answers, int[] points) { //initilizes all questions, answers, and point values
      questions[0] = "In what year was the first computer developed? ";
      questions[1] = "What year was George Washington's first year as president? ";
      questions[2] = "Who is the most overpowered champion in League of Legends? ";
      questions[3] = "What has been the worst year in the history of the United States? ";
      questions[4] = "What is the name of the medal you get for placing second? ";
      answers[0] = "1946";
      answers[1] = "1789";
      answers[2] = "yasuo";
      answers[3] = "2020";
      answers[4] = "silver";
      points[0] = 3;
      points[1] = 2;
      points[2] = 2;
      points[3] = 2;
      points[4] = 1;
   
   }
   
   public static void play(String [] questions, String[] answers, int[] points) {
      Scanner sc = new Scanner(System.in);
      int score = 0;
      for(int i = 0; i < 5; ) { //while question index is less than 5, game asks question and takes in user response
         System.out.println("Question " + (i + 1) + " :");
         System.out.print(questions[i]);
         String answ = sc.nextLine();
         answ = answ.toLowerCase();
         if(answ.compareTo(answers[i]) == 0) { //checks if user response is correct, if correct then moves on to next question
            score += points[i];
            System.out.println("You are Correct!!");
            System.out.println();
            i++;
         
         }else { //if user response is incorrect then displays correct answer and moves on to next question
            System.out.println("Sorry, that answer was incorrect.");
            System.out.println("The correct answer was: " + answers[i]);
            System.out.println();
            i++;
         
         }
         
         if(i == 5) { //once last question is displayed, game stops and prints score while also asking if another player wants to play
            System.out.println("Your score: " + score + "/10");
            System.out.println("Is there another player? y/n: ");
            String resp = sc.next();
            resp = resp.toLowerCase();
            if(resp.compareTo("y") == 0) { //if new game wants to begin, then reset score, reset question index, and restart game
               score = 0;
               i = 0;
               run();
            
            }else { //if not, then exit
               System.exit(0);
            
            }
         
         }
      
      }
      
   }

}