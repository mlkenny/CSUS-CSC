import java.lang.Math; //imports math functions like sqrt and powers
import java.util.Scanner; //imports scanner

class CircumfrenceHelper {
   public static double Distance(){ //uses scanner to recieve user integer input and uses distance formula to find radius
      Scanner user_input = new Scanner(System.in);
      int x1 = 0; int x2 = 0; int y1= 0; int y2 = 0;
      System.out.println("Please enter integer input for Point 1: x1 ");
      x1 = user_input.nextInt();
      System.out.println("Please enter integer input for Point 1: y1 ");
      y1 = user_input.nextInt();
      System.out.println("Please enter integer input for Point 2: x2 ");
      x2 = user_input.nextInt();
      System.out.println("Please enter integer input for Point 2: y2 ");
      y2 = user_input.nextInt();
      return Math.sqrt((Math.pow(x2-x1, 2)) + (Math.pow(y2-y1, 2)));
   }
   public static double FindCircumfrence(double radius){ //returns circumfrence from variable radius and PI
      return 2 * Math.PI * radius;
   }
   public static void OneCircle(){ //assigns distance to variable radius, prints radius, assigns circumfrence to variable circumfrence, prints circumfrence
      double radius = Distance();
      System.out.printf("Radius = %.2f\n", radius);
      double circumfrence = FindCircumfrence(radius);
      System.out.printf("Circumfrence = %.2f\n", circumfrence);
   }
   public static void ThreeCircles(){ //calls functions OneCircle 3 times
      for (int i = 0; i <= 2; i++){
         OneCircle();
      }
   
   }
}