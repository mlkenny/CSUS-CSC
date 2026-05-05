public class Oops4 {
    public static void main(String[] args) {
      System.out.println(season(7));
    
   }
   public static String season (int month) {
      if (month >= 1 && month < 4) {
         return "Winter";
      }else if (month >= 4 && month < 7) {
         return "Spring";
      }else if (month >= 7 && month < 10) {
         return "Summer";
      }else {
         return "Fall";
      }
   
   
   }
}