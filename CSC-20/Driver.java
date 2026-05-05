import java.util.Scanner;

public class Driver {
   public static void main(String [] args) {
      boolean b = false;
      Scanner sc = new Scanner(System.in);
      while(!b) {
         System.out.print("Enter the name: ");
         String temp_name = sc.next();
         System.out.print("Enter the hours: ");
         int temp_hours = sc.nextInt();
         System.out.print("Enter the rate: ");
         double temp_rate = sc.nextDouble();
         Payroll p1 = new Payroll(temp_name, temp_rate, temp_hours);
         try {
            System.out.println("Your pay is: " + p1.calculatePay());
            b = true;
         
         }catch(Exception e) {
            
         
         }
               
      }
   
   }
   
}

class Payroll {
   String name;
   double rate;
   int hoursWorked;
   
   public Payroll(String name, double rate, int hoursWorked) {
      try {
         if(name.equalsIgnoreCase(name) && name != null) {
            this.name = name;
         
         }else {
            throw new Exception();
         
         }
         
      }catch(Exception e) {
         System.out.println("Name is not a string.");
         System.out.println("We have to start all over again");
      
      }
      
      try {
         if(rate >= 0) {
            this.rate = rate;
         
         }else {
            throw new Exception();
         
         }
         
      }catch(Exception e) {
         System.out.println("Rate is not a positive number.");
         System.out.println("We have to start all over again");
      
      }
      
      try {
         if(hoursWorked >= 0) {
            this.hoursWorked = hoursWorked;
         
         }else {
            throw new Exception();
         
         }
         
      }catch(Exception e) {
         System.out.println("Hours Worked is not 0 nor a positive number.");
         System.out.println("We have to start all over again");
      
      }
      
   
   }
   
   public void setName(String n) {
      try {
         if(n == (String)n) {
            name = n;
         
         }
      
      }catch(Exception e) {
         System.out.println("Name is not a string.");
      
      }
   
   }
   
   public void setRate(double r) {
      try {
         if(r >= 0) {
            rate = r;
         
         }
      
      }catch(Exception e) {
         System.out.println("Rate is not a positive number.");
      
      }
   
   }
   
   public void setHours(int hours) {
      try {
         if(hours >= 0) {
            hoursWorked = hours;
         
         }else {
            throw new Exception();
         
         }
      
      }catch(Exception e) {
         System.out.println("Hours Worked is not 0 nor a positive number.");
      
      }
   
   }
   
   public double calculatePay() throws Exception {
      if(rate * hoursWorked > 0) {
         return rate * hoursWorked;
      
      }
      throw new Exception();
   
   }

}