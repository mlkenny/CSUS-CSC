class LanternsHelper{

   public static void constructTopHat(){
      System.out.println("    *****                 *****");
      System.out.println("  *********             *********");
      System.out.println("*************         *************");
   }
   public static void constructRocket(){
      constructTopHat();
      constructMidLine();
      constructTopHat();
      
   }
   public static void constructMidLine(){
      System.out.println("* | | | | | *         * | | | | | *");
   
   }
   public static void constructBottomLine(){
      System.out.println("*************         *************");
   
   }
   public static void constructGap(){
      System.out.println();
   
   }
   public static void constructFiveStarLine(){
      System.out.println("    *****                 *****");
   
   }
    public static void constructLantern(){
      constructTopHat();
      constructGap();
      constructTopHat();
      constructMidLine();
      constructBottomLine();
      constructTopHat();
      constructGap();
      constructTopHat();
      constructFiveStarLine();
      constructMidLine();
      constructMidLine();
      constructFiveStarLine();
      constructFiveStarLine();
   }
}