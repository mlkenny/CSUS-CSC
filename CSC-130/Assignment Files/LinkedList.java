// Author: Michael Kenny
// Class: CSC-130 Data Structures and Algorithm Analysis
// Prof: Devin Cook
// Assignment: Implement a working doubly linked list using a generic/simple node class that provides methodology for the following functions:
//             About - Returns information about the author.
//             IsEmpty - Returns true or false for list being empty.
//             ToString and ToStringReverse - Returns single string of all nodes in list forwards -> back and backwards -> forward.
//             AddToHead and AddToTail - Adds a node to the head in front of existing list and add to tail of existing list.
//             RemoveHead and RemoveTail - Remove node from head of list and remove tail from back of list.

public class LinkedList {
    public Node head;
    public Node tail;

    // Default constructor for linked list. Assigns empty head and tail.
    public LinkedList() {
        head = null;
        tail = head;
    }

    // About method: returns information about the author (me).
    public String About() {
        return "Author: Michael Kenny";
    }

    // ToString method: returns name of all nodes within linked list as one string separated by commas.
    // From head to tail (forwards).
    public String ToString() {
        Node current = head;
        String nodes = "";

        if(this.IsEmpty()) {
            return nodes;
        }else {
            while (current.next != null) {
                nodes = nodes + current.field.value + ", ";
                current = current.next;
            }
        }
        nodes = nodes + current.field.value;
        return nodes;
    }

    // ToStringReverse mehtod: returns name of all nodes within linked list as one string separated by commas.
    // From tail to head (backwards).
    public String ToStringReverse() {
        Node current = tail;
        String nodes = "";

        while(current.prev != null) {
            nodes = nodes + current.field.value + ", ";
            current = current.prev;
        }
        nodes = nodes + current.field.value;
        return nodes;
    }

    // IsEmpty method: checks if linked list is an empty list (head is null).
    public Boolean IsEmpty() {
        if(head == null) {
            return true;
        }
        return false;
    }
    
    // AddToHead method: adds to head of linked list (front of list).
    public void AddToHead(Field value) {
        // Create new node with given value.
        Node newHead = new Node(value);

        if(this.IsEmpty()) { // If empty list, start list with head and tail at given value.
            head = newHead;
            tail = head;
        }else { // Else add new node to front of list.
            // Adjust pointers so the new head connects to old head and vice versa. (Forward pointer, backward pointer)
            newHead.next = head;
            head.prev = newHead;
            head = newHead; // Change head pointer to new head.
        }
    }

    // AddToTail method: adds to tail of linked list (back of list).
    public void AddToTail(Field value) {
        // Create new node with given value.
        Node newTail = new Node(value);

        if(this.IsEmpty()) { // If empty list, start list with head at given value.
            head = newTail;
            tail = head;
        }else { // Else add new node to end of list.
            // Adjust pointers so the old tail connects to new tail and vice versa. (Forward pointer, backward pointer)
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail; // Changes tail pointer to added tail.
        }
    }

    // RemoveHead method: removes head from linked list, makes new head be the old head's next.
    public Field RemoveHead() {
        if(this.IsEmpty()) {
            return null; // Return null if empty list.
        }else {
            Node current = head;

            // If next is null then only one element in list, otherwise move head to next and delete previous link.
            if(current.next == null) {
                tail = null; // Nuke tail.
                head = null; // Also nuke head.
                return current.field;
            }else {
                // Else, change head to next node and delete old link to previous.
                head = current.next;
                head.prev = null; // Nuke link.
                return current.field;
            }
        }
    }

    // RemoveTail method: removes tail from linked list, makes new tail be the old tail's previous.
    public Field RemoveTail() {
        if(this.IsEmpty()) {
            return null; // Return null if empty list.
        }else {
            Node current = tail;

            // If previous is also head, then only one element in list.
            if(current.prev == head) {
                tail = null; // Nuke tail.
                head = null; // Also nuke head.
                return current.field;
            }else {
                // Else, change tail to previous and delete old link to next.
                tail = current.prev;
                tail.next = null; // Nuke link.
                return current.field;
            }
        }
    }

    // Start of Node class.
    private class Node {
        Field field; // Field will hold node value and a key.
        Node next = null;
        Node prev = null;

        private Node(Field field) {
            this.field = field;
        }
    }
}