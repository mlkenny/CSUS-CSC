import java.util.*;

public class VisaValidation {

   
   public static void main(String [] args) {
      Scanner kb = new Scanner(System.in);
      run(kb);
   
   }
   
   public static void run(Scanner kb) {
      boolean valid;
      String s = "y";
      Scanner console = new Scanner(System.in);
      System.out.println("This program will determine if you");
      System.out.println("have entered a valid credit card number");
      while (s.equalsIgnoreCase("y")) {
         System.out.print("Enter a credit card number: ");
         long number = console.nextLong();
         
         valid = isValid(number);
         if (valid) {
            System.out.println("This IS a valid credit card number");
         
         }else {
            System.out.println("This IS NOT a valid credit card number");
         
         }
         System.out.print("Would you like to validate another credit card? y/n ");
         s = console.next();
         if (s.equalsIgnoreCase("n")) {
            System.out.println("Have a nice day!");
         
         }
         
      }
      
   }
   
   public static boolean lengthValidation(long cardNumber) {
      int length = (int) Math.floor(Math.log10(cardNumber)) + 1;
      if (length == 16) {
         return true;
      
      }else {
         return false;
      
      }
      
   }
   
   public static boolean prefixDigitValidation(long cardNumber) {
      while (cardNumber > 4) {
         cardNumber = cardNumber / 10;
         
      }
      
      if (cardNumber == 4) {
            return true;
         
         }else {
            return false;
         
      }
   
   }
   
   public static int sumOfOddPlaced(long cardNumber) {
      int oddTotal = 0;
      while (cardNumber > 0) {
         long digit = cardNumber % 10;
         oddTotal += digit;
         cardNumber = (long) cardNumber / 100;
           
      }
      return oddTotal;
   
   }
   
   public static int sumOfEvenPlaced(long cardNumber) {
      int evenTotal = 0;
      cardNumber = (long) cardNumber / 10;
      while (cardNumber > 0) {
         evenTotal += cardNumber % 10;
         cardNumber = (long) cardNumber / 100;
         
      }
      return evenTotal;
   
   }
   
   public static int sumOfDoubleDigit(int number) {
      if (number % 10 == 0) {
         return number;
      
      }else {
         int new_num = (int) number / 10;
         int remain = number % 10;
         int result = new_num + remain;
         return result;
      
      }
   
   }
   
   public static boolean isValid(long cardNumber) {
      boolean length = lengthValidation(cardNumber);
      boolean prefix = prefixDigitValidation(cardNumber);
      int oddSum = sumOfOddPlaced(cardNumber);
      int evenSum = sumOfEvenPlaced(cardNumber);
      int total = oddSum + evenSum;
      int totalOfDigits = sumOfDoubleDigit(total);
      boolean div = totalOfDigits % 10 == 0;
      boolean result = length && prefix && div;
      return result;
   
   }
   
}