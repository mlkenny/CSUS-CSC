//Name: Michael Kenny
//Title: CSC Assignment #11 (CSC20 - Recursive (Recursion Practice))
//Date: 5/16/2021
//Professor: Faroughi
//Description: Create 5 individual methods that use recursion to loop through an array that do the following:
//             Test if an array of numbers is a palindrome, get sum of digits in an array, get longest word in array of strings, 
//             test if two strings are equal in characters, and get sum of digits in a linked list.

import java.util.*;
import java.io.*;

public class Recursive {

   static int sum = 0;
   public static void main(String [] args) {
      System.out.println("testing the palindrome method");
      int[] a = {5,6,4,5,8,5,4,6,5,12};
      System.out.print(Arrays.toString(a) + " is palindrome?  ");
      System.out.println(palindrome(a,0));
      int[] b = {1,2,3,4,3,2,1};
      System.out.print(Arrays.toString(b) + " is palindrome?  ");
      System.out.println(palindrome(b,0));
      System.out.println("\ntesting sum of the digits");
      int num = 12345;
      System.out.println("The sum of the digits in " + num + " is "+ sum(12345));
      System.out.println("\ntesting longest string in an array of string");
      String[] s = {"Hello","Bye","Said","song","Building"};
      System.out.println("The longest string is the array " + Arrays.toString(s) + " is " + longest(s,1,s[0]));
      System.out.println("\ntesing the equals method on the strings");
      String s1 = "hello";
      String s2 = "helloo";
      System.out.println("are the strings " + s1 + " and " + s2 +" equal? "  +equals(s1,s2,0));
      s1 = "tomorrow";
      s2 = "tomorrow";
      System.out.println("are the strings " + s1 + " and " + s2 +" equal? "  +equals(s1,s2,0));
      System.out.println("\ntesting the sum of the integers in a link list");
      LinkedList<Integer> list = new LinkedList<Integer>();
      list.add(5);
      list.add(7);
      list.add(8);
      list.add(12);
      System.out.println("The sum of the numbers in the linklist " + list + " is " + listSum(list, 0));
   
   }
   
   public static boolean palindrome(int [] a, int start) {
      int end = a.length - 1; //mark an ending point so we can end recursion.
      if(start >= end) { //if start is at end or somehow greater than the end, the word is complete.
         return true;
      
      }
      
      if(a[start] == a[end - start]) { //tests to see if each num is equal to it's correlating end num. (in {1, 2, 3, 2, 1}, the first 1's correlating 
         return palindrome(a, start + 1); //if nums equal each other then go to next set of nums         end num is 1, 2 is 2, and 3 is 3.)
      
      }else {
         return false;
      
      }
      
   
   }
   
   public static int sum(int n) {
      int sum = n % 10; //mods num by 10 which gives last digit of num.
      if(n == 0) { //if num is 0, which means there is no next num bigger than 0, num is done.
         return 0;
      
      }else {
         return sum + sum(n / 10);//if num is not done, add that num to the next num as long as that num is not mod 0 (recursive).  
      
      }
   
   }
   
   public static String longest(String [] s, int index, String longest) {
      if(index + 1 > s.length) { //measures to see if index is equal to the length meaning the last word has been checked.
         return longest;
      
      }
      
      if(s[index].length() > longest.length()) { //tests if length of another string is longer than the current longest string.
         longest = s[index];
      
      }
      
      return longest(s, index + 1, longest); //moves to next string.
   
   }
   
   public static boolean equals(String s1, String s2, int index) {   
      if(s1.length() > s2.length()) { //tests if first string is longer than second, returns not equal.
         return false;
      
      }else if(index + 1 > s1.length() && index + 1 > s2.length()) { //if index is greater than the length of both strings, returns equal.
         return true;
      
      }
      
      if(index >= s1.length() || index >= s2.length()) { //if index is greater than or equal to the length of either string, return not equal.
         return false;
      
      }
      
      return equals(s1, s2, index + 1);
      
   }
   
   public static int listSum(LinkedList<Integer> list, int index) {
      if(list.get(index) == null) { //filters out any null indicies.
      
      
      }else {
         sum += (int)list.get(index); //adds each num at given index to sum.
      
      }
      
      if(index >= list.size() - 1) { //if index is greater than the end of the list, returns current sum. (list is over)
         return sum;
      
      }
      
      return listSum(list, index + 1); //if end of list has not been met, recursive call for next index.
      
   }

}