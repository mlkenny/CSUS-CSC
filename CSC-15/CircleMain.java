//Name: Michael Kenny
//Title: Lab #12 (CSC15 - Circle Class Constructors)
//Date: 11/16/2020
//Description: Write a circle class and use a constructor to create said circle with a given center and radius.
//             Write another class in order to use constructors to find the area and circumference of the given circle.
public class CircleMain{

   public static void main(String [] args) {
      Circle circleOne = new Circle(); //circle one constructor object
      Circle circleTwo = new Circle(); //circle two constructor object
      circleOne.circle(2, 5, 8);
      circleTwo.circle(-5, -6, 9);
      System.out.println("Circle One's Current X, Y, and Radius Values: " + circleOne.x + ", " + circleOne.y + ", "+ circleOne.radius);
      System.out.println("Circle Two's Current X, Y, and Radius Values: " + circleTwo.x + ", " + circleTwo.y + ", "+ circleTwo.radius);
      System.out.println();
      System.out.println("Circle One's Area: " + circleOne.calcArea(circleOne.radius));
      System.out.println("Circle Two's Area: " + circleTwo.calcArea(circleTwo.radius));
      System.out.println();
      System.out.println("Circle One's Circumference: " + circleOne.calcCircumference(circleOne.radius));
      System.out.println("Circle Two's Circumference: " + circleTwo.calcCircumference(circleTwo.radius));
      circleOne.setX(4);
      circleOne.setY(5);
      circleTwo.setRadius(11);
      System.out.println();
      System.out.println("Circle One w/ changed center: " + circleOne.toString(circleOne.x, circleOne.y, circleOne.radius));
      System.out.println("Circle Two w/ changed radius: " + circleTwo.toString(circleTwo.x, circleTwo.y, circleTwo.radius));
      
   
   }

}