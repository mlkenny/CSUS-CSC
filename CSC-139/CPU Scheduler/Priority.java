/*
 * Name: Michael Kenny
 * Date: 10/1/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Scheduling Programming Assignment - Priority Scheduling Algorithm
 * Objective/Goal: Write an algorithm using the provided interface that implements the
 *                 priority queue scheduling algorithm. This algorithm is FCFS except with highest priority tasks
 *                 taking the highest presedence.
 * Note:
 *      All tasks will be read via Driver.java into the readyQueue of the constructor.
*/

import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Priority implements Algorithm {
    // Ready Queue (FIFO)
    List<Task> readyQ;

    // Priority Constructor
    public Priority(List<Task> readyQueue) {
        this.readyQ = readyQueue;

        // Sort queue by priority in descending order.
        // Easily done so by using a collection or List.sort with comparator lambda.
        Collections.sort(this.readyQ, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                // t2.priority - t1.priority => sorts from highest priority -> lowest priority
                return Integer.compare(t2.getPriority(), t1.getPriority());
            }
        });
    }

    // Schedule Method
    public void schedule() {
        // Keep looping through ready queue until it is empty.
        // Empty ready queue signifies all tasks are done.
        while (!readyQ.isEmpty()) {
            // pickNextTask returns task at front of queue which
            // is the current highest priority task.
            Task currentTask = pickNextTask();

            // Since this algorithm is non pre-emptive, tasks cannot be interrupted.
            // Therefore we run the entire task to completion.
            CPU.run(currentTask, currentTask.getBurst()); // Executes task for burst time.
            System.out.println(currentTask.getName() + " has terminated.\n"); // For test tracking purposes.
        }
    }

    // Pick Next Task Method
    public Task pickNextTask() {
        // Returns the first element of the ready queue.
        // Since we sorted by priority, front of queue will be highest priority.
        // If multiple tasks have same priority,
        // it will pick first based on order all tasks were added to ready queue.
        return readyQ.remove(0); // ArrayList.remove() method returns and removes the element from the list at once.
    }
}
