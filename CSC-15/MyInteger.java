//Name: Michael Kenny
//Title: Programming Assignment #8 (CSC15 - MyInteger Class)
//Date: 12/8/2020
//Description: Design a class named MyIntger that contains methods for an isEven, isOdd, isPrime functions 
//             as well as equals methods for checking two values amd a parseInt function that converts a char array or string into an int
public class MyInteger{

   private int value;
   
   public MyInteger(int oldValue) { //initial constructor assigns given value to private int variable
      value = oldValue;
   
   }
   
   public boolean isEven() { //function evaluates constructed number as even
      if(value % 2 == 0) {
         return true;
      
      }
      return false;
      
   }
   
   public boolean isOdd() { //function evaluates constructed number as odd
      if(value % 2 == 1) {
         return true;
      
      }
      return false;
      
   }
   
   public boolean isPrime() { //function evaluates constructed number as prime
      if(value <= 1) {
         return false;
      
      }else if(value == 2) {
         return true;
      
      }else if(value % 2 == 0) {
         return false;
      
      }
      for(int i = 3; i <= Math.sqrt(value); i += 2) {
         if(value % i == 0) {
            return false;
         
         }
      
      }
      return true;
      
   }
   
   public static boolean isEven(int value) { //function evaluates value passed as parameter as even
      if(value % 2 == 0) {
         return true;
      
      }
      return false;
      
   }
   
   public static boolean isOdd(int value) { //function evaluates value passed as parameter as odd
      if(value % 2 == 1) {
         return true;
      
      }
      return false;
      
   }
   
   public static boolean isPrime(int value) { //function evaluates value passed as parameter as prime
      if(value <= 1) {
         return false;
      
      }else if(value == 2) {
         return true;
      
      }else if(value % 2 == 0) {
         return false;
      
      }
      for(int i = 3; i <= Math.sqrt(value); i += 2) {
         if(value % i == 0) {
            return false;
         
         }
      
      }
      return true;
      
   }
   
   public boolean equals(int num) { //function evaluates number passed as parameter as equal to constructed number
      if(num == value) {
         return true;
      
      }else {
         return false;
      
      }
   
   }
   
   public static int parseInt(char [] arr) { //parses character array into string then into int using ASCII
      String num = "";
      for(int i = 0; i < arr.length; i++) {
         num = num + arr[i];
      
      }
      int answer = 0;
      int factor = 1;
      for(int i = num.length(); i > 0; i--) {
         answer += (num.charAt(i - 1) - '0') * factor;
         factor *= 10;
      
      }
      return answer;
      
   
   }
   
   public static int parseInt(String num) { //parses string into int using ASCII
      int answer = 0;
      int factor = 1;
      for(int i = num.length(); i > 0; i--) {
         answer += (num.charAt(i - 1) - '0') * factor;
         factor *= 10;
      
      }
      return answer;
      
   
   }


}