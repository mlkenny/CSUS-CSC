import java.util.Scanner;

public class Calculator {

   public static void main(String [] args) {
      algorithm();

   }
   public static void algorithm() {
      Scanner sc = new Scanner(System.in);
      System.out.print("Please enter first number: ");
      int x = sc.nextInt();
      if (x == 8008135) {
         System.exit(1);
      }
      System.out.println();
      System.out.print("Please enter an operation: ");
      char op = sc.next().charAt(0);
      System.out.println();
      System.out.print("Please enter second number: ");
      int y = sc.nextInt();
      System.out.println();
      
      while (true) {
         if (op == '+') {
            int answer = x + y;
            System.out.print("Answer: " + answer);
            System.out.println();
            System.out.println();
            algorithm();
         }else if (op == '-') {
            int answer = x - y;
            System.out.print("Answer: " + answer);
            System.out.println();
            System.out.println();
            algorithm();
         }else if (op == '*') {
            int answer = x * y;
            System.out.print("Answer: " + answer);
            System.out.println();
            System.out.println();
            algorithm();
         }else if (op == '/') {
            double answer = x / y;
            System.out.print("Answer: " + x + " / " + y + " or about " + answer);
            System.out.println();
            System.out.println();
            algorithm();
         }
      }
   
   }
    
}