import java.util.*;

public class ListNode {
   private int data;
   public ListNode next;
   public ListNode(int data) {
      this.data = data;
      
   }
   
   public ListNode(int data, ListNode next) {
     this.data = data;
     this.next = next;
     
   }
   
   public int getData() {
      return data;
      
   }
   
   public ListNode getNext() {
      return next;
      
   }
   
   public void setData(int data) {
     this.data = data;
     
   }
   
   public void setNext(ListNode n) {
      next = n;
      
   }
   
}

class LinkedIntList {
   public ListNode front;
   private int size = 0;
  // creating a list with the first node having a value
   public LinkedIntList(int value) {
      front = new ListNode(value);
      
   }
   
   //creating an empty list
   public LinkedIntList() {
     front = null; 
     
   }
   
   public void append(int data) {
      ListNode current = front;
      while(current.next != null) {
         current = current.next;
      
      }
      current.next = new ListNode(data);
   
   }
   
}

class Driver {
   public static void main (String [] args) {
      LinkedIntList list = new LinkedIntList(80);
      list.append(7);

   }

}

