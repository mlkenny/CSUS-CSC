public class min{
   public static void main(String [] args){
      min(19, 27, 6);   
      ForLoop();
   
   }
   
   public static void min(int num1, int num2, int num3){
      int min = Math.min((Math.min(num1, num2)), num3);
      System.out.println(min);
   
   }

}
   public static void ForLoop(String [] args){
   
      for (int i = 1; i <= 2; i++){
         for(int j = 1; j <= 3; j++){
            for (int k = 1; k <= 4; k++){
               System.out.println("*");
            
            }
         
         }
         System.out.println("!");
      
      }
   
      System.out.println();
   }

}