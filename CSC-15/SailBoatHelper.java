class SailBoatHelper{
   public static void sail(){ //basically draws sail using repeating lines of asterisks
   System.out.println("       *");
   System.out.println("       **");
   System.out.println("       ***");
   System.out.println("       ****");
   System.out.println("       *****");
   System.out.println("       ******");
   }

   public static void boat(){ //draws basic shape of the boat, needed escape sequences for \
   System.out.println(" ------------");
   System.out.println(" \\          /");
   System.out.println("  \\        /");
   System.out.println("   --------");
   }

   public static void water(){ //draws two lines of hyphens used to illustrate water
   System.out.println(" ------------");
   System.out.println(" ------------");
   }
   
   public static void drawSailBoat(){ //compiles the methods required to construct the image
   sail();
   boat();
   water();
   }
}