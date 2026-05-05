public class Car{
   public String make;
   public String model;
   public String color;
   public String year;
   public int speed = 0;
   public boolean engineOn = false;
   public int miles = 0;
   
   public Car() {
      make = "";
      model = "";
      color = "";
      year = "";
      speed = 0;
      engineOn = false;
   
   }
   
   public Car(String new_make, String new_model, String new_color, String new_year) {
      make = new_make;
      model = new_model;
      color = new_color;
      year = new_year;
   
   }
   
   public String getMake() {
      return make;
   
   }
   
   public String getModel() {
      return model;
   
   }
   
   public String getYear() {
      return year;
   
   }
   
   public String getColor() {
      return color;
   
   }
   
   public int getSpeed() {
      return speed;
   
   }
   
   public boolean getEngineOn() {
      return engineOn;
   
   }
   
   public int getMiles() {
      return miles;
   
   }
   
   public void setMake(String new_make) {
      make = new_make;
   
   }
   
   public void setModel(String new_model) {
      model = new_model;
   
   }
   
   public void setColor(String new_color) {
      color = new_color;
   
   }
   
   public void setYear(String new_year) {
      year = new_year;
   
   }
   
   public void setSpeed(int new_speed) {
      speed = new_speed;
   
   }
   
   public void start() {
      engineOn = true;
   
   }
   
   public void stop() {
      engineOn = false;
   
   }
   
   public void move() {
      miles += speed;
   
   }
   
   public void move(int hours) {
      miles += speed * hours;
   
   }
   
   public void accelerate() {
      if(speed > 0 && speed < 100) {
         speed += 5;
      
      }
   
   }
   
   public void brake() {
      if(speed > 0) {
         speed -= 5;
      
      }
   
   }
   
   public String toString() {
      return "Make " + make + ": Model " + model + ": Year " + year + ": Color " + color;
   
   }


}