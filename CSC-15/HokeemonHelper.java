import java.util.*;
import java.io.*;

public class HokeemonHelper {
   
   public static final int SIZE = 8;
   
   public static Hokeemon[] getData() {
     Hokeemon[] hokeeList = new Hokeemon[SIZE];
     try {
         Scanner sc = new Scanner(new File("Data.txt"));
         while(sc.hasNextLine()) { //assigns each new line to a new hokeemon object
            for(int i = 0; i < SIZE ; i++) {
               hokeeList[i] = new Hokeemon(sc.next(), sc.next(), sc.nextInt());
            
            }
         
         }
     
     } catch (IOException e){
         System.out.println(e.getMessage());
     
     }
     return hokeeList;
   
   }
   
   public static String getBio(Hokeemon me, Hokeemon[] hokeeList) {
      String bio = "I am " + me.toString().substring(6) + ", I live in the " + me.livesIn();
      String friends = "\nMy friends are: ";
      for(int i = 0; i < SIZE; i++) { //paths through hokeelist and checks for compatible friends and adds compatible types to friends list
         if(me.areFriends(hokeeList[i])) {
            friends = friends + hokeeList[i].getName() + " ";
      
         }
      
      }
      return bio + friends;
   
   }

}