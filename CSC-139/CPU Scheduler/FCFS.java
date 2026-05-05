/*
 * Name: Michael Kenny
 * Date: 10/1/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Scheduling Programming Assignment - FCFS Scheduling Algorithm
 * Objective/Goal: Write an algorithm using the provided interface that implements the
 *                 First Come First Serve scheduling algorithm.
 * Note:
 *      All tasks will be read via Driver.java into the readyQueue of the constructor.
*/

import java.util.List;

public class FCFS implements Algorithm {
    // Ready Queue (FIFO)
    List<Task> readyQ;

    // FCFS Constructor
    public FCFS(List<Task> readyQueue) {
        this.readyQ = readyQueue;
    }
    
    // Schedule Method
    public void schedule() {
        // Keep looping through ready queue until it is empty.
        // Empty ready queue signifies all tasks are done.
        while(!readyQ.isEmpty()) {
            // Use next task method to return first task from ready queue.
            Task currentTask = pickNextTask();

            // Since this algorithm is non pre-emptive, tasks cannot be interrupted.
            CPU.run(currentTask, currentTask.getBurst()); // Executes task for entire burst time.
            System.out.println(currentTask.getName() + " has terminated.\n"); // For test tracking purposes.
        }
    }

    // Pick Next Task Method
    public Task pickNextTask() {
        // Returns the first element of the ready queue.
        // "First come first serve".
        return readyQ.remove(0); // ArrayList.remove() method returns and removes the element from the list at once.
    }
}
