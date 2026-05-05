// Author: Michael Kenny
// Class: CSC-130 Data Structures and Algorithm Analysis
// Prof: Devin Cook
// Assignment: Implement Herman Hollerith's Radix Sort using alterations of the previous LinkedList class we built.
//             Features Required:
//             Key-Value Nodes
//             Modified LinkedList interface
//             Create Queue class based on LinkedList interface.
//                    Implement for Queue:
//                              Enqueue - Returns a void, enqueues a field onto the queue. (AddtoTail from LL)
//                              Dequeue - Returns a field, dequeues a field from the queue. (removeHead from LL)
//                              isEmpty - Returns a boolean, returns true if queue is empty.
//             Implement Radix Sort using bucket queues. (1 main queue, 10 bucket queues, can use array of 10 queues)

public class Queue {
    // Instantiate a main queue using linked list implementation.
    LinkedList queue = new LinkedList();

    // About method: Returns about section from Linked List class.
    public String About() {
        return queue.About();
    }

    // ToString method: Returns the queue as a string.
    public String ToString() {
        return queue.ToString();
    }

    // ToStringReverse method: Returns the queue as a reversed string.
    public String ToStringReverse() {
        return queue.ToStringReverse();
    }

    // IsEmpty method: The same as LinkedList.
    public boolean IsEmpty() {
        return queue.IsEmpty();
    }

    // Enqueue method: By adding to tail we add the item to the back of the line.
    public void Enqueue(Field item) {
        queue.AddToTail(item);
    }

    // Dequeue method: Dequeing an item removes it from the front of the line.
    public Field Dequeue() {
        return queue.RemoveHead();
    }
}