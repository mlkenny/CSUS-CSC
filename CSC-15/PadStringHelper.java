import java.util.Scanner;

class PadStringHelper{
   public static void padString() {
      Scanner in = new Scanner(System.in); //constructs scanner called in
      System.out.print("Please enter a string: "); //asks for user input string
      String string = in.next(); //connects user input string to string variable called string
      int len = string.length(); //takes length of user input string and assigns to int variable called len
      System.out.print("Please enter an integer: "); //aks for user input integer
      int num = in.nextInt(); //connects user input next integer to int variable called num
      System.out.print("The resulting string is: "); //prints spit back message for result
      for (int i = len; i < num; i++) { //for loop that prints dashes based on integer minus string length
         System.out.print("-" + "");
      }
      System.out.print(string); //prints string
      
  }
}