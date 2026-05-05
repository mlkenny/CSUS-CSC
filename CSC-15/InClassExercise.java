public class InClassExercise{

   public static void main(String [] args){
      sphereVolume(10);
      truncate();
      multipleOfSeven(700);
   
   
   }
   
   public static void sphereVolume(int r){
      double V = 4.0/3*3.14*r*r*r;
      System.out.println(V);
   
   }
   public static void truncate(){
     double x = 3.4567;
     x = x * 100;
     x = (int) x;
     x = x / 100; 
     System.out.println("X = "+x);
   
   }
   public static void multipleOfSeven(int num){
      int x = num % 7;
      if (x == 0) {
         System.out.println("True");
      
      }
      else {
         System.out.println("False");
      }
   
   }
   
}