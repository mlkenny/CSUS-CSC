import java.util.Scanner;
import java.lang.*;

public class Gah{
   public static void main(String [] args){
   
   }
   
   public static int quadrant(double x, double y) {
      if (x > 0 && y > 0) {
         return 1;
      
      }else if (x > 0 && y < 0) {
         return 4;
      
      }else if (x < 0 && y > 0) {
         return 2;
      
      }else if (x < 0 && y < 0) {
         return 3;
      
      }else {
         return 0;
      
      }
   
   }
   
   public static int numUnique(int a, int b, int c) {
      if (a != b && b != c && a != c) {
         return 3;
      
      }else if (a == b && b == c) {
         return 1;
      
      }else if (a == b || b == c || a == c) {
         return 2;
      
      }else {
         return 0;
      
      }
   
   }
   
   public static int wordCount(String s) {
      int word_count = 0;
      boolean word = false;
      int endOfWord = s.length() - 1;
      
      for (int i = 0; i < s.length(); i++) {
         if (Character.isLetter(s.charAt(i)) && i != endOfWord) {
            word = true;
         
         }else if (!Character.isLetter(s.charAt(i)) && word) {
            word_count++;
            word = false;
         
         }else if (Character.isLetter(s.charAt(i)) && i == endOfWord) {
            word_count++;
          
         }
         
      }
      return word_count;
   
   }
   
   public static void LastName() {
      Scanner sc = new Scanner(System.in);
      System.out.print("Please input your last name: " + " ");
      String last_name = sc.next();
      last_name = last_name.toLowerCase();
      char check = last_name.charAt(0);
      System.out.println("Your last name is: "+ last_name);
      System.out.println();
      if (check == 'm') {
         System.out.println("Your name starts with an m!");
      
      }else if (check == 'z') {
         System.out.println("Your name starts with a z!");
      
      }else {
         System.out.println("Your name does not start with m or z.");
      
      }
   
   }
   
   public static double pow2(double base, int exp) {
      double result = 1;
      for (int i = 0; i < Math.abs(exp); i++) {
        result *= base;
        
      }
      
      if (exp < 0) {
        return 1 / result;
      
      }
      
      return result;
      
   } 
   
   public static int countFactors(int n) {
      for (int i = 1; i <= n; i++) {
         if (n % i == 0) {
            return i;
         
         }
      
      }
      return 0;
   
   }
   
   public static void word() {
    String s = "hello";
    String result = "";
    for(int i = 0; i < s.length(); i++)
    {
        char c= s.charAt(i);
        c = (char)(c + ('A' - 'a'));
        result = result + c;
    }
    System.out.println(result);

   }
   
   int [] dagayones = {9, 5, 3, -1, 4};
   
   public static double average(int [] dagayones){
      double av = 0;
      for (int i = 0; i < dagayones.length; i++) {
         av += dagayones[i];
      
      }
      av = av / dagayones.length;
      return av;
   
   }
   

}
