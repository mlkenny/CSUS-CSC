//Name: Michael Kenny
//Title: CSC Assignment #1 (CSC20 - Distinct Numbers)
//Date: 2/4/2021
//Professor: Faroughi
//Description: Write a program that reads in ten numbers from the user and displays the distinct numbers. 
//             If a number entered by the user appears multiple times, your program needs to filter the repeated numbers and therefore
//             displaying the repeated numbers only once. Ex: if user input is (1, 4, 6, 1, 8, 4, 2, 1, 3, 9) then the output of your program should be
//             then the output of your program should be: (1, 4, 6, 8, 2, 3, 9).

import java.util.Scanner; //imports scanner obj

class DistinctNumbers {
   public static void main(String args[]) {
      int[] num = new int[10];
      num = getInput();
      display(num);
   
   }
   
   public static boolean found(int[] num, int n) {
      boolean flag = false; //initilize flag to be false
      for(int i = 0; i <= num.length - 1; i++) { //for int i = 0 up to 1 less than the array size, if array at i equates to a num n, sets flag true
         if(num[i] == n){
            flag = true;
         
         }
      
      }
      return flag;
   
   }
   
   public static int[] getInput() {
      int[] num = new int[10];
      Scanner in = new Scanner(System.in);
      int temp;
      System.out.println("Welcome to the distinct numbers.");
      for(int i = 0; i <= num.length - 1; i++) {
         boolean flag;
         System.out.print("Please enter an integer number: ");
         temp = in.nextInt(); //gets user input from console
         if(!found(num, temp)) { //if user input is not found in preexisting array, adds user input to array, if it is found then decrement control variable (i)
            num[i] = temp;
         
         }else {
            i--;
         
         }
         
      
      }
      System.out.println("I will remove the repeated numbers and display the numbers you just entered: ");
      
      return num;
   
   }
   
   public static void display(int[] num) {
      System.out.println("Here is the list of your numbers: ");
      for(int i = 0; i <= num.length - 1; i++) {
         if(i == num.length - 1) { //if num at i is last in array then print just num, otherwise print num plus comma and space (for organization purposes)
            System.out.print(num[i]);
         
         }else {
            System.out.print(num[i] + ", ");
         
         }
      
      }
   
   }

}