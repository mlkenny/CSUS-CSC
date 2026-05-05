/*
 * Name: Michael Kenny
 * Date: 10/1/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Scheduling Programming Assignment - PriorityRR Scheduling Algorithm
 * Objective/Goal: Write an algorithm using the provided interface that implements the
 *                 Round Robin scheduling algorithm while also accounting for priority.
 *                 If a priority level has one task, it will execute that task non pre-emptively,
 *                 if a priority level has more than one task, it will execute them using round robin
 *                 with a quantum of 10 time units.
 * Note:
 *      All tasks will be read via Driver.java into the readyQueue of the constructor.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriorityRR implements Algorithm {
    // Ready Queue (FIFO), really only used for construction.
    List<Task> readyQ;

    // Master list of all priority queues, stores priority level groups in queues (FIFO).
    ArrayList<List<Task>> prioQueues = new ArrayList<List<Task>>();

    // PriorityRR Constructor
    public PriorityRR(List<Task> readyQueue) {
        this.readyQ = readyQueue;

        // Sort queue by priority in descending order.
        // This is exactly the same as for the Priority.java implementation.
        Collections.sort(this.readyQ, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                // t2.priority - t1.priority => sorts from highest priority -> lowest priority
                return Integer.compare(t2.getPriority(), t1.getPriority());
            }
        });

        // Now is when it gets weird...

        // Priority Sorting Algorithm
        // I devised this algorithm and thought it was clever, so I wanted to use it.
        // This probably adds a little bit of processing overhead, but this section sorts tasks
        // by their priority into queues with similar priority tasks.
        // This way we can do round robin on queues that have a size > 1, and run the enirety of the single size queues.

        // Counter will act as a marker for how far after the current task we move. More
        // on this in while section.
        int counter = 0;

        for(int i = 0; i < readyQ.size(); i++) {
            ArrayList<Task> list = new ArrayList<Task>();
            counter = 0;
            if(i + 1 == readyQ.size()) {
                // End of list reached, add last element to queue then break.
                list.add(readyQ.get(i));
                prioQueues.add(list);
                break;
            } else if(readyQ.get(i).getPriority() != readyQ.get(i + 1).getPriority()) {
                // Current task and next task do not match priority.
                // Add current task to its own list, by itself.
                list.add(readyQ.get(i));
            } else {
                // Current task and next task DO match priority.
                while(readyQ.get(i + counter).getPriority() == readyQ.get(i + 1 + counter).getPriority()) {
                    // Okay so this is messy and maybe a bit of spaghetti going on here.. but hear me out.
                    // Counter acts as a second "for" loop essentially, continuously adding tasks to the list
                    // while the priorities continue to match.
                    list.add(readyQ.get(i + counter)); // Add first to list.
                    counter++;
                    if(i + 1 + counter == readyQ.size()) {
                        // Next task is out of bounds, therefore current task is last in list.
                        list.add(readyQ.get(i + counter)); // Add it then break out of while.
                        break;
                    }else if(readyQ.get(i + counter).getPriority() != readyQ.get(i + 1 + counter).getPriority()) {
                        // Next task does not match priority of current task.
                        list.add(readyQ.get(i + counter)); // Add task to list then break out of while loop.
                        break;
                    } else {
                        continue;
                    }
                }
            }
            i = i + counter; // This step is critical since it updates the out-most loop variable i to reflect counter.

            // Lastly, add the new queue to the prioQueues list.
            // This simple line works since we sorted initially so all tasks will be dealt in descending priority order.
            // Therefore adding lists to prioQueue will append them such that list(0) will be highest priority.
            prioQueues.add(list);
        }
    }

    public void schedule() {
        // Priority Round Robin

        // Very similar implementation as in the original round robin.
        // This is where I break the rules a little bit.
        // I learned a little too late that my algorithm and my implementation required
        // a more specific method for picking the next task,
        // since I had so many queues to work with.
        // So to fix this "issue" I created, I decided to implement a blank
        // implementation of the abstract method
        // and implement my own overloaded method with a list parameter.

        int timeQuantum = 10; // For RR

        for(int i = 0; i < prioQueues.size(); i++) {
            if(prioQueues.get(i).size() == 1) {
                // pickNextTask returns task at front of given queue,
                // which will be the next task of the given queue's priority level.
                Task currentTask = pickNextTask(prioQueues.get(i)); // i = 0 is highest priority
                
                CPU.run(currentTask, currentTask.getBurst());
                System.out.println(currentTask.getName() + " has terminated.\n"); // For test tracking purposes.
            } else {
                // Round Robin on all elements of prioQueues.get(i)
                while (!prioQueues.get(i).isEmpty()) {
                    // pickNextTask returns task at front of given queue,
                    // which will be the next task of the given queue's priority level.
                    Task currentTask = pickNextTask(prioQueues.get(i));

                    // Calculate the remaining time for the current task.
                    // If remainingTime < 0, then task will terminate this iteration for remaining burst.
                    int remainingTime = currentTask.getBurst() - timeQuantum;

                    if (remainingTime > 0) {
                        // If remainingTime > 0, task still has more time to execute.

                        // Run currentTask by the timeQuantum.
                        CPU.run(currentTask, timeQuantum);

                        // Set current task burst time to new remaining time.
                        currentTask.setBurst(remainingTime);

                        // Add task back to the queue to be completed later. Not finishing the task
                        // and adding the task back to the queue acts as an "interrupt"
                        // in combination with running it for the timeQuantum. Making this pre-emptive.
                        prioQueues.get(i).add(currentTask);
                    } else {
                        // If remainingTime == 0, implies remaining time is 0.
                        // Because of the tasks we are testing with, remainingTime should never be less than 0.
                        // In such a case, we run the currentTask for the remainder of its burst.
                        CPU.run(currentTask, currentTask.getBurst());

                        System.out.println(currentTask.getName() + " has terminated.\n"); // For test tracking purposes.
                    }

                }
            }
        }
    }

    // Pick Next Task Abstract Implementation
    public Task pickNextTask() {
        // So this is the method implementation I decided to go with here.
        // I attempted to try and override the method but when I realized it wasn't that simple I decided
        // to just have this version of the method throw an exception if this is somehow called.
        throw new UnsupportedOperationException("PriorityRR: Usage is pickNextTask(List<Task>), interface method was overloaded.");
    }

    // Pick Next Task Method
    public Task pickNextTask(List<Task> list) {
        // This is identical to the rest of the pickNextTask methods in the rest of the project files,
        // although the only difference is that the queue the task will need to be picked from changes from
        // where in the code a task is needed.
        // I deemed it necessary to overload it and pass the queue that I wanted the task to be chosen from instead.
        return list.remove(0); // ArrayList.remove() method returns and removes the element from the list at once.
    }
}
