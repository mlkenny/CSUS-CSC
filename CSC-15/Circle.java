public class Circle {

   int x;
   int y;
   int radius;
   
   public void circle(int x, int y, int radius) { //constructs new circle based on x, y, and radius
      setX(x);
      setY(y);
      setRadius(radius);
   
   }
   
   public void setX(int newX) { //sets newX parameter as new x variable
      x = newX;
   
   }
   
   public int getX() { //returns x value when called
      return x;
   
   }
   
   public void setY(int newY) {
      y = newY;
   
   }
   
   public int getY() {
      return y;
   
   }
   
   public void setRadius(int newRadius) {
      radius = newRadius;
   
   }
   
   public int getRadius() {
      return radius;
   
   }
   
   public double calcArea(int radius) { //returns area based on circle's radius
      return Math.PI * (Math.pow(radius, 2));
   
   }
   
   public double calcCircumference(int radius) { //returns circumference based on circle's radius
      return 2 * Math.PI * radius;
   
   }
   
   public String toString(int x, int y, int radius) { //returns string token displaying given circle's center and radius
      return "[(" + x + " , " + y + "): "+ radius + "]";
   
   }


}