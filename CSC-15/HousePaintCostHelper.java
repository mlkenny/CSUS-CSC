import java.util.Scanner;
import java.util.*;

class HousePaintCostHelper {

   public static void calculateWholeHousePaintCost() { //main method that calls calculation methods and formats feedback
      double roof_cost = calculateRoofCost(); //assigns roof cost method to roof cost variable
      System.out.printf("The cost of painting the roof with red paint is: $%.2f", roof_cost); //prints roof cost variable
      System.out.println(); //all these double printlns are spacing gaps
      System.out.println();
      double base_cost = calculateBaseCost(); //assigns base cost method to base cost variable
      System.out.printf("The cost of painting the base with white paint is: $%.2f", base_cost); //prints base cost variable
      System.out.println();
      System.out.println();
      double chimney_cost = calculateChimneyCost(); //assigns chimney cost method to chimney cost variable
      System.out.printf("The cost of painting the chimney with black paint is: $%.2f", chimney_cost); //prints chimney cost variable
      System.out.println();
      System.out.println();
      double total_cost = roof_cost + base_cost + chimney_cost; //sums up final cost of all prices
      System.out.printf("With a small margin of error at the fault of rounding, the total cost painting of the house is about: $%.2f", total_cost); //prints out total cost and adjusts decimal
      
   }
   
   public static final double ANGLE = (Math.PI / 3); //public constant angle in terms of radians
   public static final double BLACK_PAINT = 1.35; //public constant of black paint cost
   public static final double WHITE_PAINT = 2.99; //public constant of white paint cost
   public static final double RED_PAINT = 3.49; //public constant of red paint cost
   
   public static int calculateBaseSurfaceArea() { //calculates base surface area given a scanner that detects width, height, and length of the base
      Scanner in = new Scanner(System.in);
      System.out.print("Please enter a height of the house: ");
      int height = in.nextInt();
      System.out.print("Please Enter the width of the house: ");
      int width = in.nextInt();
      System.out.print("Please Enter the length (depth) of the house: ");
      int length = in.nextInt();
      int base_surface_area = (width * height * 2) + (length * height * 2);
      System.out.println("The surface area of the base in cubic feet is: " + base_surface_area);
      return base_surface_area;
      
   }
   
   public static double calculateChimneySurfaceArea() { //calculates chimney surface area given a scanner that detects height and radius of chimney
      Scanner in = new Scanner(System.in);
      System.out.print("Please Enter the height of the chimney: ");
      int chimney_height = in.nextInt();
      System.out.print("Please Enter the radius of the chimney: ");
      int chimney_radius = in.nextInt();
      double chimney_surface_area = 2 * Math.PI * chimney_radius * chimney_height; 
      double x = Math.round(chimney_surface_area * 100);
      double z = x / 100;
      System.out.println("The surface area of the chimney in cubic feet is: " + z);
      return z;

   }
   
   public static double calculateRoofSurfaceArea() { //calculates roof surface area given a scanner that detects length and width of the roof
      Scanner in = new Scanner(System.in);
      System.out.print("Please Enter the length of the side of the roof: ");
      int roof_length = in.nextInt();
      System.out.print("Please Enter the width (depth) of the roof: ");
      int roof_width = in.nextInt();
      double roof_panel_surface_area = 2 * (roof_width * roof_length * Math.sin(ANGLE)); //calculates main panel area
      double bot = roof_length * Math.cos(ANGLE);
      double s = Math.round(roof_length + bot); //uses cosine function to find all three sides of triangle
      double roof_triangle_surface_area = Math.sqrt(s * Math.pow(s - roof_length, 2) * (s - (2 * roof_length * Math.cos(ANGLE)))); //calculates surface area of the triangles based on heron's formula
      double roof_surface_area = (2 * roof_triangle_surface_area) + roof_panel_surface_area; //calculates roof surface area by adding panel surface area with triangle surface area
      double x = Math.round(roof_surface_area * 100);
      double z = x / 100; //appends double value two decimal spaces
      System.out.println("The surface area of the roof in cubic feet is: " + z);
      return z;
   
   }
   
   public static double calculateBaseCost() { //finds cost to paint base with white paint but multiplying surface area and price of paint, returns cost
      double base_cost = calculateBaseSurfaceArea() * WHITE_PAINT;
      return base_cost;
      
   }
   
   public static double calculateChimneyCost() { //finds cost to paint chimney with black paint but multiplying surface area and price of paint, returns cost
      double chimney_cost = calculateChimneySurfaceArea() * BLACK_PAINT;
      return chimney_cost;
      
   }
   
   public static double calculateRoofCost() { //finds cost to paint roof with red paint but multiplying surface area and price of paint, returns cost
      double roof_cost = calculateRoofSurfaceArea() * RED_PAINT;
      return roof_cost;
      
   }
   
}