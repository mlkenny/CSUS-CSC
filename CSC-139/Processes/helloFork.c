/*
 * Name: Michael Kenny
 * Date: 9/14/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Processes - helloFork
 * Objective/Goal: Write another program using fork(). The child process should print “hello”; the parent process should print “goodbye”.
 * 		   You should try to ensure that the child process always prints first; can you do this without calling wait() in the parent?
*/

#include <stdio.h>
#include <unistd.h>

int main() {
	pid_t pid;
	
	// Create child process.
	pid = fork();

	if(pid == 0) {
		// Start of Child Process
		printf("Hello\n");
		return 0;
	} else {
		// Start of Parent Process
		// Using sleep here to give the parent process some sort of
		// simulation of a "task", otherwise it is not guaranteed for
		// the parent process to complete after the child.
		sleep(1);
		printf("Goodbye.\n");
	}

	return 0;
}
