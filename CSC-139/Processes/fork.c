/*
 * Name: Michael Kenny
 * Date: 9/14/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Processes - fork
 * Objective/Goal: Write a program that calls fork(). Before calling fork(), have the main process access a variable (e.g., x)
 *                 and set its value to something (e.g., 100).
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
	// Initialize variables.
	int value;
	pid_t pid;
	
	// Set arbitrary value.
	value = 100;
	
	// Fork child.
	pid = fork();
	

	if(pid == 0) {
		// Child process will always have pid = 0.
		// Change value for child, then print pid and value.
		value = 200;
		printf("child: %d\n", pid);
		printf("value: %d\n", value);
		// Child must be killed in order to terminate.
		exit(0);
	} else {
		// Parent process will have pid = their actual pid.
		// Keep value for parent the same (100), then print.
		printf("parent: %d\n", pid);
		printf("value: %d\n", value);
		// Change value for parent now.
		// Originally was set to 100 but for the second part
		// of the question we change the value in both processes.
		value = 300;
		printf("changed value:%d\n", value);
	}

	return 0;
}
