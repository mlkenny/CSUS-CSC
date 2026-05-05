public class Wheel {

   String brand;
   String color;
   String year;
   double price = 0;
   double radius = 1.0;
   double width = 1.0;
   boolean replace = false;
   double mileage = 0.0;
   
   public void wheel(String color, String brand, String year, double radius, double width, double price) {
      setColor(color);
      setBrand(brand);
      setYear(year);
      setRadius(radius);
      setWidth(width);
      setPrice(price);
   
   }
    
    public double getCircumference(double radius) {
      return Math.PI * 2 * radius;
    
    }
    
    public void setBrand(String newBrand) {
      brand = newBrand;
    
    }
    
    public String getBrand() {
      return brand;
    
    }
    
    public void setYear(String newYear) {
      year = newYear;
    
    }
    
    public String getYear() {
      return year;
    
    }
    
    public void setColor(String newColor) {
      color = newColor;
    
    }
    
    public String getColor() {
      return color;
    
    }
    
    public void setRadius(double newRadius) {
      radius = newRadius;
    
    }
    
    public double getRadius() {
      return radius;
    
    }
    
    public void setWidth(double newWidth) {
      width = newWidth;
    
    }
    
    public double getWidth() {
      return width;
    
    }
    
    public void setMileage(double miles) {
      mileage = miles;
      checkMileage();
    
    }
    
    public double getMileage() {
      return mileage;
    
    }
    
    public void checkMileage() {
      if(mileage > 40000) {
         replace = true;
      
      }
    
    }
    
    public void setPrice(double newPrice) {
      price = newPrice;
    
    }
    
    public double getPrice() {
      return price;
    
    }
    
    public void move(double miles) {
      mileage += miles;
    
    }
    
    public static boolean isBigger(Wheel w1, Wheel w2) {
      if(w1.radius > w2.radius) {
         return true;
      
      }
      return false;
    
    }
    
    public static boolean isSameSize(Wheel w1, Wheel w2) {
      if(w1.radius == w2.radius && w1.width == w2.width) {
         return true;
      
      }
      return false;
    
    }
    
    public static boolean isCheaper(Wheel w1, Wheel w2) {
      if(isSameSize(w1, w2) && w1.price < w2.price) {
         return true;
      
      }
      return false;
    
    }
    
    public String toString() {
      return "Brand " + brand + ": Radius " + radius + ": Width " + width + ": Color " + color + ": Mileage " + mileage + ": Price$ " + price;
    
    }

}