public class CarDriver {
   public static void main(String [] args) {
      Car bradsCar = new Car("Honda", "Civic", "Silver", "2019");
      Car jensCar = new Car("Toyota", "Camry", "Blue", "2016");
      System.out.println("Brad's Car: " + bradsCar.toString() +  " " + bradTrip(bradsCar, 5) + " miles");
      System.out.println("Jen's Car: " + jensCar.toString() + " " + jenTrip(jensCar, 5) + " miles");
   
   }
   
   public static int bradTrip(Car car1, int days) {
      car1.start();
      car1.setSpeed(40);
      car1.move();
      car1.brake();
      car1.move();
      car1.stop();
      
      car1.start();
      car1.setSpeed(50);
      car1.accelerate();
      car1.move(2);
      car1.stop();
      
      return car1.getMiles() * days;
   
   }
   
   public static int jenTrip(Car car2, int days) {
      car2.start();
      car2.setSpeed(60);
      car2.move();
      
      car2.accelerate();
      car2.move();
      
      car2.brake();
      car2.move(2);
      car2.stop();
      
      return car2.getMiles() * days;
   
   }


}