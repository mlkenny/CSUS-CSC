import java.util.Scanner;

public class PeopleData {
   
   public static void main(String [] args) {
      Scanner sc = new Scanner(System.in);
      String[] nameArray = new String[10];
      int[] ageArray = new int[10];
      inputData(sc, nameArray, ageArray);
      checkAgeRange(ageArray);
      calculateStimulusAmount(nameArray, ageArray);
   
   }
   
   public static void inputData(Scanner sc, String[] nameArray, int[] ageArray) {
      for(int i = 0; i < 10; i++) {
         System.out.print("Please enter customer name: ");
         String name = sc.next();
         if(name == " ") {
            break;
         
         }
         nameArray[i] = name;
         System.out.print("Please enter customer age: ");
         ageArray[i] = sc.nextInt();
         
      
      }
      System.out.println("Sorry, no room for more.");
   
   }
   
   public static int checkAgeRange(int[] ageArray) {
      int min = ageArray[0];
      int max = ageArray[0];
      for(int i = 0; i < ageArray.length - 1; i++) {
         if(ageArray[i] < ageArray[i + 1]) {
            min = ageArray[i];
         
         }else if(ageArray[i] > ageArray[i + 1]) {
            max = ageArray[i];
         
         }
      
      }
      return max - min;
   
   }
   
   public static void calculateStimulusAmount(String[] nameArray, int[] ageArray) {
      for(int i = 0; i < ageArray.length; i++) {
         int stimAmount = 0;
         if(ageArray[i] < 18) {
            stimAmount = 0;
         
         }else if(ageArray[i] >= 18 && ageArray[i] < 21) {
            stimAmount = 500;
         
         }
         else if(ageArray[i] >= 21 && ageArray[i] < 55) {
            stimAmount = 1000;
         
         }else if(ageArray[i] >= 55 && ageArray[i] < 65) {
            stimAmount = 1500;
         
         }else if(ageArray[i] >= 65) {
            stimAmount = 2000;
         
         }
         
         System.out.println("Name: " + nameArray[i] + " Age: " + ageArray[i] + " Amount: $" + stimAmount);
      
      }
   
   }
   

}