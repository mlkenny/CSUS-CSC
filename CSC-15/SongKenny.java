//Name: Michael Kenny
//Title: Lab4 (CSC15 - SongLyrics)
//Date: 9/25/2020

public class SongKenny {

   public static void main (String [] args) {
      lyric();
   }
   
   public static int BOTTLES = 10; // constant value of bottles
   public static void lyric (){
      for (int i = BOTTLES; i >= 2; i--) { // displays lyric based on current bottle value, also displays next bottle value
         System.out.println(i + " bottles of the Juice on the wall, "+ i +" bottles of juice");
         System.out.println("Take one down and pass it around, "+ (i-1) + " bottles of juice on the wall.");
      
      }
      for(int j = 1; j > 0; j--) { // displays final portion of lyric, finishes song by taking bottle value and subtracting 1
         System.out.println("1 bottle of juice on the wall, 1 bottle of juice");
         System.out.println("Take one down and pass it around, no more bottles of juice on the wall");
         System.out.println("Go to the store and buy some more, "+ (BOTTLES-j)+" bottles of juice on the wall.");
      }
   
   }
}