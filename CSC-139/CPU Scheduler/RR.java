/*
 * Name: Michael Kenny
 * Date: 10/1/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Scheduling Programming Assignment - RR Scheduling Algorithm
 * Objective/Goal: Write an algorithm using the provided interface that implements the
 *                 Round Robin scheduling algorithm using a quantum of 10 time units.
 * Note:
 *      All tasks will be read via Driver.java into the readyQueue of the constructor.
*/

import java.util.List;

public class RR implements Algorithm {
    // Ready Queue (FIFO)
    List<Task> readyQ;

    // Should be 10, according to assignment instructions.
    int timeQuantum = 10; // This is our time slice to split up task allocation fairly.

    // RR Constructor
    public RR(List<Task> readyQueue) {
        this.readyQ = readyQueue;
    }

    // Schedule Method
    public void schedule() {
        // Round Robin

        // Keep looping through ready queue until it is empty.
        // Empty ready queue signifies all tasks are done.
        while(!readyQ.isEmpty()) {
            // pickNextTask returns task at front of queue which
            // will just be on a first come first serve basis.
            // Round robin operates by cycling tasks in and out of the queue.
            Task currentTask = pickNextTask();

            // Calculate the remaining time for the current task.
            // If remainingTime < 0, then task will terminate this iteration for remaining burst.
            int remainingTime = currentTask.getBurst() - timeQuantum;
            
            if(remainingTime > 0) {
                // If remainingTime > 0, task still has more time to execute.

                // Run currentTask by the timeQuantum.
                CPU.run(currentTask, timeQuantum);

                // Set current task burst time to new remaining time.
                currentTask.setBurst(remainingTime);

                // Add task back to the queue to be completed later. Not finishing the task
                // and adding the task back to the queue acts as an "interrupt"
                // in combination with running it for the timeQuantum. Making this pre-emptive.
                readyQ.add(currentTask);
            } else {
                // If remainingTime == 0, implies remaining time is 0.
                // Because of the tasks we are testing with, remainingTime should never be less than 0.
                // In such a case, we run the currentTask for the remainder of its burst.
                CPU.run(currentTask, currentTask.getBurst());

                System.out.println(currentTask.getName() + " has terminated.\n"); // For test tracking purposes.
            }
            
        }
    }

    // Pick Next Task Method
    public Task pickNextTask() {
        // Returns the first element of the ready queue.
        // "FSFS"
        return readyQ.remove(0); // ArrayList.remove() method returns and removes the element from the list at once.
    }
}
