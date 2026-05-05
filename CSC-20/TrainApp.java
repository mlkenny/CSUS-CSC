//Name: Michael Kenny
//Title: CSC Assignment #5 (CSC20 - Train App)
//Date: 3/6/2021
//Professor: Faroughi
//Description: Amtrack is asking to create an application to help them keep track of passengers list in a given train. 
//             Program must be able to: display info of passengers, delete existing passenger, add new passenger, count amount of passengers currently in train,
//             search list of passengers with specific last name, and display all names of current passengers.

import java.util.*;

class Driver {
   public static void main(String [] args) {
      Train myTrain = new Train();
      Passenger p1 = new Passenger("Alex", "Mano", "123-456-7893", "Mano@gmail.com", 12, "First Class");
      Passenger p2 = new Passenger("Mary", "Trump", "123-456-4894", "mary@sierracollege.edu", 23, "Coach Class");
      Passenger p3 = new Passenger("Al", "Busta", "123-456-7890", "AlB@csus.edu", 34, "Business Class");
      Passenger p4 = new Passenger("Jose", "Rodriguez", "123-222-7890", "Jose@gmail.com", 22, "First Class");
      Passenger p5 = new Passenger("Joe", "Rodriguez", "123-222-7890", "joe@gmail.com", 25, "First Class");
      myTrain.add(p1);
      myTrain.add(p2);
      myTrain.add(p3);
      myTrain.add(p4);
      myTrain.add(p5);
      System.out.print("Here is a list of the passengers: ");
      System.out.println();
      System.out.println(myTrain);
      System.out.println("Testing printLast Method: " + "\n");
      for(int i = 0; i < myTrain.count; i++) {
         System.out.println(myTrain.train[i].getLast());
      
      }
      System.out.println();
      System.out.println("Testing static method getCount: ");
      System.out.println("The train has " + myTrain.count + " Passengers." + "\n");
      Scanner in = new Scanner(System.in);
      System.out.println("Testing the search method: ");
      System.out.print("Enter a Last Name for use in Search: ");
      String n = in.next();
      System.out.println("\n" + "Here is the info of Passenger " + n + ":");
      System.out.println(myTrain.search(n));
      System.out.println("Testing the delete method: ");
      System.out.print("Enter a Last Name to Delete from Passenger List: ");
      n = in.next();
      boolean b = myTrain.delete(n);
      if(b) {
         System.out.println("Passenger " + n + " has been deleted.");
      
      }
      System.out.println("\n" + "Here is an updated list of the Passengers: ");
      System.out.println(myTrain);
      System.out.println();
      System.out.println("The train now has " + myTrain.count + " Passengers.");
   
   }

}

class Person {
   
   String name;
   String last_name;
   String phone;
   String email;
   
   public Person() { //default constructor
      
   
   }
   
   public Person(String name, String last_name, String phone, String email) { //parameterized constructor
      this.name = name;
      this.last_name = last_name;
      this.phone = phone;
      this.email = email;
   
   }
   
   public void setFirst(String first) {
      name = first;
   
   }
   
   public void setLast(String last) {
      last_name = last;
   
   }
   
   public void setPhone(String number) {
      phone = number;
   
   }
   
   public void setEmail(String new_email) {
      email = new_email;
   
   }
   
   public String getFirst() {
      return name;
   
   }
   
   public String getLast() {
      return last_name;
   
   }
   
   public String getPhone() {
      return phone;
   
   }
   
   public String getEmail() {
      return email;
   
   }
      
   public String toString() {
      return "First Name: " + name + "\nLast Name: " + last_name + "\nPhone Number: " + phone + "\nEmail: " + email;
   
   }
   
   public boolean equals(Person o) {
      return o instanceof Person;
   
   }

}

class Passenger extends Person {
   
   private int seatNumber;
   private String status;
   
   public Passenger() { //default constructor w/o params
      super();
   
   }
   
   public Passenger(String first, String last, String phone, String email, int num, String s) { //parameterized constructor
      super(first, last, phone, email);
      this.seatNumber = num;
      this.status = s;
   
   }
   
   public void setClass(String s) {
      status = s;
   
   }
   
   public void setSeatNumber(int number) {
      seatNumber = number;
   
   }
   
   public int getSeatNumber() {
      return seatNumber;
   
   }
   
   public String getStatus() {
      return status;
   
   }
   
   public String toString() {
      return "\n" + super.toString() + "\nSeat Number: " + seatNumber + "\nClass: " + status + "\n"; //takes super to string from parent class to add to current to string
   
   }

}

class Train extends Passenger {

   public static int count = 0;
   Passenger[] train;
   int trainNumber;
   
   public Train() { //default constructor, sets passenger array to array of size 10
      super();
      train = new Passenger[10];
   
   }
   
   public Train(String first, String last, String phone, String email, int num, String s) { //parameterized constructor
      super(first, last, phone, email, num, s);
   
   }
   
   public Passenger[] getTrain() {
      return train;
   
   }
   
   public int getTrainNumber() {
      return trainNumber;
   
   }
   
   public void setTrainNumber(int num) {
      trainNumber = num;
   
   }
   
   public void add(Passenger p) { //if p is a passenger object, then add it to array and increment count
      if(p instanceof Passenger) {
         train[count] = p;
         count++;
      
      }
   
   }
   
   public Object search(Object o) { //if object o is of type string, return correct name according to if o == some train[i] last name, else returns null if not found
      boolean b = o instanceof String;
      if(b) {
         String name = (String)o;
         for(int i = 0; i < count; i++) {
            if(train[i] != null && train[i].getLast().equalsIgnoreCase(name)) {
               return train[i];
         
         }
      
      }
      
      }else {
         return null;
      
      }
      return null;
   
   }
   
   public boolean delete(Object o) { //if object o is of type string, delete passenger from train and return true if o == some train[i] last name. Also decrements count if name matches
      boolean b = o instanceof String;
      if(b) {
         String name = (String)o;
         for(int i = 0; i < count; i++) {
            if(train[i] != null && train[i].getLast().equalsIgnoreCase(name)) {
               train[i] = null;
               count--;
               return true;
         
         }
      
      }
      
      }
      return false;
   
   }
   
   public void printLast() { //if some train[i] last name is of type string then prints all last names of train[i]
      for(int i = 0; i < count; i++) {
         boolean b = train[i].getLast() instanceof String;
         if(b) {
            System.out.println(train[i].getLast());
         
         }
      
      }
   
   }
   
   public String toString() { //using string builder to append strings, as long as train[i] is not a null index, will append all valid passenger objects to string, then returns that string 
      StringBuilder s = new StringBuilder();
      for(int i = 0; i <= count; i++) {
         if(train[i] != null) {
            s.append(train[i].toString());
         
         }
               
      }
      return s.toString();
   
   }
   
   public static int getCount() {
      return count;
   
   }

}