//Name: Michael Kenny
//Title: CSC Lab #13 (CSC15 - Hokeemon (Magical Creatures))
//Date: 11/16/2020
//Description: Write a program that scans a text file for a name, type, and age 
//             and creates hokeemon object for each line and displays bio plus
//             friends list depending on type association.
import java.util.*;
import java.io.*;

class HokeemonMain {
   
   public static final int SIZE = 8;
   public static void main(String [] args) {
      
      Hokeemon[] hokeeList = new Hokeemon[SIZE];
      
      hokeeList = HokeemonHelper.getData();
      System.out.println(Arrays.toString(hokeeList));
      System.out.println();
      for(int i = 0; i < SIZE; i++) {
         System.out.println(HokeemonHelper.getBio(hokeeList[i], hokeeList));
      
      }
   
   }


}