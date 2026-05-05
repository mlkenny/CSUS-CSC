public class WheelDriver {

   public static void main(String [] args) {
      Wheel w1 = new Wheel();
      Wheel w2 = new Wheel();
      Wheel w3 = new Wheel();
      
      w1.wheel("Silver", "BigO", "2019", 26, 2, 49.99);
      w2.wheel("Blue", "SpeedTrack", "2016", 28, 1, 59.99);
      w3.wheel("Black", "Racer", "2020", 26, 2, 39.99);
      
      w1.move(1000);
      w2.move(20000);
      w3.move(30000);
      
      w1.move(20000);
      w2.move(20000);
      w3.move(20000);
      
      System.out.println("Mileage Wheel One: " + w1.mileage);
      System.out.println("Mileage Wheel Two: " + w2.mileage);
      System.out.println("Mileage Wheel Three: " + w3.mileage);
      
      if(w1.replace) {
         System.out.println("Wheel One needs to be replaced");
      
      }else if(w2.replace) {
         System.out.println("Wheel Two needs to be replaced");
      
      }else if(w3.replace) {
         System.out.println("Wheel Three needs to be replaced");
      
      }
      
      if(Wheel.isBigger(w1, w2) || Wheel.isSameSize(w1, w2)) {
         if(Wheel.isBigger(w1, w2) && Wheel.isBigger(w1, w3)) {
            System.out.println("Wheel one is bigger than both wheel's one and three");
         
         }
         System.out.println("Wheel one is bigger than wheel two or they are the same size");
      
      }else if(Wheel.isBigger(w1, w3) || Wheel.isSameSize(w1, w3)) {
         System.out.println("Wheel one is bigger than wheel three but not wheel two");
      
      }else if(Wheel.isBigger(w2, w3) || Wheel.isSameSize(w2, w3)){
         System.out.println("Wheel two is either bigger or the same size as wheel three");
      
      }
      
      if(Wheel.isCheaper(w1, w2)) {
         System.out.println("Wheel one is the same size and cheaper");
      
      }else {
         System.out.println("Wheel one and two are either not the same size, or not the same price");
      
      }
      
      System.out.println(w1.toString());
      System.out.println(w2.toString());
      System.out.println(w3.toString());
      
      w1.getMileage();
      w2.getMileage();
      w3.getMileage();
      
   
   }

}