import java.util.Scanner;

class SwitchCalculatorHelper {
   public static void run() { //main algorithm that prints menu as well as calls all methods
      Scanner in = new Scanner(System.in);
      try { //try catch here to catch any exceptions at the fault of user error
         System.out.print("How many times would you like to run the program? " + " ");
         int reps = in.nextInt();
         for (int i = 0; i < reps; i++) {
            menu();
            System.out.println();
            System.out.print("Enter the operation: " + " ");
            String op = in.next();
            System.out.print("Enter a number between 0-9: " + " ");
            int first_num = in.nextInt();
            System.out.print("Enter a number between 0-9: " + " ");
            int second_num = in.nextInt();
            System.out.println();
            int answer = calculate(first_num, second_num, op);
            System.out.println(convertOperand(first_num) + convertOperation(op) + convertOperand(second_num) + "is " + answer);
         
         }
         
      }catch (Exception e) { //catches any exception and returns a print message and restarts the program
         System.out.println("You have entered an invalid character, please try again.");
         System.out.println();
         run();
         
      }
   
   }
   
   public static void menu() { //prints menu
      System.out.println("*******************************");
      System.out.println("To do addition enter + ");
      System.out.println("To do multiplication enter * ");
      System.out.println("To do subtraction enter - ");
      System.out.println("To do exponent enter ^ ");
      System.out.println("To do division enter / ");
      System.out.println("To do modulus enter % ");
      System.out.println("*******************************");
   
   }
   
   public static int calculate(int opr1, int opr2, String operation) { //switch statement allows user to choose an operation based off a string
      switch (operation) {
         case "+": 
            return opr1 + opr2;
                   
         case "*": 
            return opr1 * opr2;
                   
         case "-": 
            return opr1 - opr2;
                   
         case "^": 
            return (int)Math.round(Math.pow(opr1, opr2));
                   
         case "/": 
            return (int)(opr1 / opr2);
                   
         case "%": 
            return (int)(opr1 % opr2);
                   
         default: System.out.println("You have entered an invalid operation. ");
            run();
            return 0;
                   
      }
   
   }
   
   public static String convertOperation(String operation) { //converts operations from symbols to words
      if (operation.equals("+")) {
         return "plus ";
      
      }else if (operation.equals("-")) {
         return "minus ";
      
      
      }else if (operation.equals("*")) {
         return "times ";
      
      
      }else if (operation.equals("/")) {
         return "divided by ";
      
      
      }else if (operation.equals("^")) {
         return "to the power of ";
      
      
      }else {
         return "mod ";
      
      }
   
   }
   
   public static String convertOperand(int opr) { //converts numbers from numerical values to strings
      if (opr == 1) {
         return "one ";
      
      }else if (opr == 2) {
         return "two ";
      
      }else if (opr == 3) {
         return "three ";
      
      }else if (opr == 4) {
         return "four ";
      
      }else if (opr == 5) {
         return "five ";
      
      }else if (opr == 6) {
         return "six ";
      
      }else if (opr == 7) {
         return "seven ";
      
      }else if (opr == 8) {
         return "eight ";
      
      }else if (opr == 9) {
         return "nine ";
      
      }else {
         return "zero ";
      
      }
      
   }

 }