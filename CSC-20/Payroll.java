//Name: Michael Kenny
//Title: CSC Assignment #3 (CSC20 - Payroll Constructors)
//Date: 2/21/2021
//Professor: Faroughi
//Description: Write a program that constructs objects for payroll according to a person's specialized name, id, hourly rate, and hours worked. 
//             There should be getters and setters used to manipulate and control the objects. There should be a to String method allowing easy printing
//             of the object's information.

public class Payroll {

   String name;
   String id;
   double hoursWorked;
   double hourlyRate;

   public Payroll(String name, String id, double hourlyRate, double hoursWorked) {
      this.name = name;
      this.id = id;
      this.hoursWorked = hoursWorked;
      this.hourlyRate = hourlyRate;
   
   }
   
   public String getName() {
      return name;
   
   }
   
   public String getId() {
      return id;
   
   }
   
   public double getHoursWorked() {
      return hoursWorked;
   
   }
   
   public double getHourlyRate() {
      return hourlyRate;
   
   }
   
   public void setName(String setname) {
      name = setname;
   
   }
   
   public void setHoursWorked(double hours) {
      hoursWorked = hours;
   
   }
   
   public void setHourlyRate(double rate) {
      hourlyRate = rate;
   
   }
   
   public double getPay() {
      return hourlyRate * hoursWorked;
   
   }
   
   public String toString() {
      return "Name: " + name + "\nID: " + id + "\nHours worked: " + hoursWorked + "\nHourly rate: " + hourlyRate;
   
   }
   
}

//once you implement the Payroll class, uncommnet the code in the main method and run your program. 

class PayrollDriver {
  public static void main(String[] args) {
   
    System.out.println("Creating payroll objects");
    Payroll p1 = new Payroll("Alex Martinez" ,"123456", 25, 20);
    Payroll p2 = new Payroll("Ali Santos" ,"986747", 125, 45);
    Payroll p3 = new Payroll(" Jose Busta" ,"45678", 55, 30);
    System.out.println("testing the toString method");
    System.out.println("List of the employees");
    System.out.println(p1);
    System.out.println("Salary is : " + p1.getPay());  //calling the getPay method
    System.out.println("\n*******************");
    System.out.println(p2);
    System.out.println("Salary is : "+ p2.getPay());
    System.out.println("\n*******************");
    System.out.println(p3);
    System.out.println("Salary is : "+ p3.getPay());
    System.out.println("\n*******************");
    System.out.println("\nTesting the setter methods");
    System.out.println("The hourly pay of " + p1.getName()  + " is being changed");
    p1.setHoursWorked(80);  // changing the hours worked for the object p1
    System.out.println(p1);                   
    
    
    
    //***********************************************************************************
    
    
    /*        Your turn , you need to implement code for each commnet  below       */
     
    Payroll p4 = new Payroll("John Smith", "123321456654", 160, 40);
    Payroll p5 = new Payroll("Soulja Boy", "1", 580, 25);
    
    System.out.println("\nDisplaying Objects: ");
    System.out.println(p4 + "\n");
    System.out.println("Salary is : " + p1.getPay());
    System.out.println(p5 + "\n");
    System.out.println("Salary is : " + p1.getPay());
    
    System.out.println("Displaying Salary: ");
    System.out.println(p4.getName() + "'s salary is: " + p4.getPay());
    System.out.println(p5.getName() + "'s salary is: " + p5.getPay() + "\n");
    
    System.out.println("Changing Hourly Rate...");
    p4.setHourlyRate(100);
    p5.setHourlyRate(1000);
    
    System.out.println();
    
    System.out.println("Displaying Objects with new Hourly Rate: ");
    System.out.println(p4);
    System.out.println("Salary is : " + p4.getPay() + "\n");
    System.out.println(p5);
    System.out.println("Salary is : " + p5.getPay() + "\n");
    
    System.out.println("Changing Hours Worked...");
    p4.setHoursWorked(35);
    p5.setHoursWorked(40);
    
    System.out.println();
    
    System.out.println("Displaying Objects with new Hours Worked: ");
    System.out.println(p4);
    System.out.println("Salary is : " + p4.getPay() + "\n");
    System.out.println(p5);
    System.out.println("Salary is : " + p5.getPay() + "\n");
    
    System.out.println("Changing Clientele Name..." + "\n");
    p4.setName("Bill Gates");
    p5.setName("LeBron James");
    
    System.out.println("Displaying Objects with new Names: ");
    System.out.println(p4);
    System.out.println("Salary is : " + p4.getPay() + "\n");
    System.out.println(p5);
    System.out.println("Salary is : " + p5.getPay());
    
  }
  
}