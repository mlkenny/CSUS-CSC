/*
 * Name: Michael Kenny
 * Date: 10/1/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Scheduling Programming Assignment - SJF Scheduling Algorithm
 * Objective/Goal: Write an algorithm using the provided interface that implements the
 *                 Shortest Job First scheduling algorithm.
 * Note:
 *      All tasks will be read via Driver.java into the readyQueue of the constructor.
*/

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF implements Algorithm {
    // Ready Queue (FIFO)
    List<Task> readyQ;

    // SJF Constructor
    public SJF(List<Task> readyQueue) {
        this.readyQ = readyQueue;

        // Sorts the queue of tasks by smallest burst time.
        // This is only possible since we know the burst times of every task
        // beforehand. Otherwise extra overhead might be required for each 
        // pick task call.
        // Easily done so by using a collection or List.sort with comparator lambda.
        Collections.sort(this.readyQ, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                // t1.burst - t2.burst => sorts from shortest - largest
                return Integer.compare(t1.getBurst(), t2.getBurst());
            }
        });
    }

    // Schedule Method
    public void schedule() {
        // Keep looping through ready queue until it is empty.
        // Empty ready queue signifies all tasks are done.
        while (!readyQ.isEmpty()) {
            // Use next task method to return first task from ready queue.
            // Since we sorted by burst time, front of queue will always
            // be the shortest job.
            Task currentTask = pickNextTask();

            // Since this algorithm is non pre-emptive, tasks cannot be interrupted.
            CPU.run(currentTask, currentTask.getBurst()); // Executes task for burst time.

            System.out.println(currentTask.getName() + " has terminated.\n"); // For test tracking purposes.
        }
    }

    // Pick Next Task Method
    public Task pickNextTask() {
        // Return the task at the front of the ready queue.
        // Since we sorted by burst time, front of queue will be shortest job.
        return readyQ.remove(0); // ArrayList.remove() method returns and removes the element from the list at once.
    }
}
