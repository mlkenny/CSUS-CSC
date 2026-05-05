//Name: Michael Kenny
//Title: CSC Assignment #10 (CSC20 - Integer Queue)
//Date: 5/3/2021
//Professor: Faroughi
//Description: Create a queue system that behaves like a stack, only being able to push items to the back and pop them off from the front.
//             The queue should be able to be manipulated in order to search through the stack and find the minimum value, maximum value,
//             and average value of all numbers in the stack. The queue should also be able to reverse itself and determine if a stack is sorted. 

import java.util.*;

public class Queue {
   ArrayList<Integer> list;
   
   public Queue() { //Default Constructor, initializes array list
      list = new ArrayList<Integer>();
   
   }
   
   public void enqueue(int num) { //adds num to array list
      list.add(num);
   
   }
   
   public int dequeue() { //removes first num from array list
      return list.remove(0);
   
   }
   
   public String toString() {
      String s = "";
      Queue copy = new Queue();
      boolean b = false;
      while(!b) {
         try { 
            int num = this.dequeue(); //pops num off stack
            copy.enqueue(num); //pushes num to new stack
            s = s + " " + num; //puts num into string
         
         }catch(Exception e) {
            b = true;
         
         }
      
      }
      restore(copy); //restores "new stack" to be current stack
      return s;
   
   }
   
   public int getMax() {
      Queue copy = new Queue();
      boolean b = false;
      int max = 0; //initialize at 0, start low build up
      while(!b) {
         try {
            int num = this.dequeue(); //pops num off stack
            if(num > max) { //evaluates if num is greater than current max
               max = num; //if so then num becomes the new max
            
            }
            copy.enqueue(num); //pushes num to new stack
         
         }catch(Exception e) {
            b = true;
         
         }
      
      }
      restore(copy); //restores "new stack" to be current stack
      return max;
   
   }
   
   public void restore(Queue q) {
      boolean b = false;
      while(!b) {
         try {
            this.enqueue(q.dequeue()); //takes parameterized queue and pushes it to be the current (this) stack
         
         }catch(Exception e) {
            b = true;
         
         }
      
      }
   
   }
   
   public Integer getMin() {
      Queue copy = new Queue();
      boolean b = false;
      int min = 500; //initialize at high num, high anchor point
      while(!b) {
         try {
            int num = this.dequeue(); //pops num off stack
            if(num < min) { //evaluates if num is less than current min
               min = num; //if so then num becomes new min
            
            }
            copy.enqueue(num); //pushes num to new stack
         
         }catch(Exception e) {
            b = true;
         
         }
      
      }
      restore(copy); //restores "new stack" to be current stack
      return min;
   
   }
   
   public void reverse() {
      Stack<Integer> stack = new Stack<Integer>();
      boolean b = false;
      while(!b) {
         try {
            int num = this.dequeue(); //pops num off stack (queue)
            stack.push(num); //pushes num into real stack
         
         }catch(Exception e) {
            b = true;
         
         }
      
      }
      b = false; //reset variable b
      while(!b) {
         try {
            int num = stack.pop(); //pop num off real stack
            this.enqueue(num); //pushes num to stack (queue)
         
         }catch(Exception e) {
            b = true;
         
         }
      
      }
   
   }
   
   public double getAverage() {
      double sum = 0;
      double average = 0;
      Queue copy = new Queue();
      boolean b = false;
      while(!b) {
         try {
            int num = this.dequeue(); //pops num off stack
            sum += num; //add equals num to sum
            copy.enqueue(num); //push num onto new stack
         
         }catch (Exception e) {
            b = true;
         
         }
      
      }
      restore(copy); //restores "new stack" to be current stack
      average = sum / this.list.size(); //evaluates the average value by dividing the sum of the stack by the size of the stack
      return average;
   
   }
   
   public boolean isSorted() {
      Queue q = new Queue();
      boolean b = false;
      boolean sorted = true;
      while(!b) {
         try {
            Integer n1 = this.dequeue(); //pops one num off stack
            Integer n2 = this.dequeue(); //pops a second num off stack
            q.enqueue(n1); //pushes first num to new stack
            q.enqueue(n2); //pushes second num to new stack
            if(n1 > n2) { //evaluate if first num is greater than second num
               sorted = false; //if so then list is out of order, if not then while will continue
                              //to run and determine if all nums are in increasing order
            
            }
         
         }catch(Exception e) {
            b = true;
         
         }
      
      }
      restore(q); //restores "new stack" to be current stack
      return sorted;
   
   }
   
}

class Driver {
   public static void main(String[] args) {
      Queue m = new Queue();
      m.enqueue(10);
      m.enqueue(12);
      m.enqueue(15);
      m.enqueue(7);
      m.enqueue(100);
      m.enqueue(22);
      System.out.println("The queue is :" + m);
      //test reverse
      m.reverse();
      System.out.println("The queue in reverse order is :" + m);
      m.reverse();
      //test average
      System.out.printf("Average = %.2f\n", m.getAverage());
      //test max
      System.out.println("Max = " + m.getMax());
      //test min
      System.out.println("Min = " + m.getMin());
      //test sorted method
      System.out.println("The list is sorted: " + m.isSorted());
      
      //New Sorted Stack Print
      Queue n = new Queue();
      n.enqueue(1);
      n.enqueue(2);
      n.enqueue(3);
      n.enqueue(4);
      n.enqueue(5);
      n.enqueue(6);
      System.out.println("\nThe queue is :" + n);
      n.reverse();
      System.out.println("The queue in reverse order is :" + n);
      n.reverse();
      System.out.printf("Average = %.2f\n", n.getAverage());
      System.out.println("Max = " + n.getMax());
      System.out.println("Min = " + n.getMin());
      System.out.println("The list is sorted: " + n.isSorted());
     
   }

}