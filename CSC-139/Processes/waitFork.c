/*
 * Name: Michael Kenny
 * Date: 9/14/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Processes - waitFork
 * Objective/Goal: Now write a program that uses wait() to wait for the child process to finish in the parent.
 * 		   What does wait() return? What happens if you use wait() in the child?
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
	pid_t pid;

	pid = fork();

	if(pid < 0) {
		// Fork Failed
		printf("waitFork: fork has failed\n");
		return 1;
	} else if (pid > 0) {
		// Parent Process
		pid_t value = wait(NULL);
		printf("waitFork: This is the parent process\n");
		printf("waitFork: Parent wait received: %d\n", value);
		return 0;
	} else {
		// Child Process
		pid_t value = wait(NULL);
		printf("waitFork: This is the child process\n");
		printf("waitFork: Child wait received: %d\n", value);
		return 0;	
	}

	return 0;
}
