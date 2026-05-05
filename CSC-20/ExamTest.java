import java.util.*;

public class ExamTest {
   public static void main(String [] args) {
      isPrime(18, 2);
      isPrime(19, 2);
   
   }
   public static boolean isPrime(int num, int fact) {
      if(num % fact == 0) {
         return false;
      
      }
      if(fact * fact > num) {
         return true;
      
      }
      return isPrime(num, fact + 1);
   
   }
   
   public static void calculateTuition(double tuition, int year, double total) {
      if(year > 5) {
         System.out.println("Total tuition for 5 years is: " + total);
      
      }else if(year == 1) {
         tuition = (2 * tuition);
         System.out.println("Year " + year + " tuition: " + tuition);
         total += tuition;
         calculateTuition(tuition, year + 1, total);
      
      }else {
         tuition = tuition + (tuition * 0.025);
         System.out.println("Year " + year + " tuition: " + tuition);
         total += tuition;
         calculateTuition(tuition, year + 1, total);
      
      }


   }

}