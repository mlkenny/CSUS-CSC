/*
 * Name: Michael Kenny
 * Date: 9/14/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Processes - waitPIDFork
 * Objective/Goal: Write a slight modification of the previous program, this time using waitpid() instead of wait().
 * 		   When would waitpid() be useful?
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
	pid_t pid;
	int status;

	pid = fork();

	if(pid < 0) {
		// Fork Failed
		printf("waitPIDFork: fork has failed\n");
		return 1;
	} else if (pid > 0) {
		// Parent Process
		pid_t value = waitpid(pid, &status, 0);
		printf("waitPIDFork: This is the parent process: %d\n", pid);
		printf("waitPIDFork: Parent wait received: %d\n", value);
		return 0;
	} else {
		// Child Process
		//pid_t value = waitpid();
		printf("waitPIDFork: This is the child process: %d\n", pid);
		//printf("waitPIDFork: Child wait received: %d\n", value);
		return 0;	
	}

	return 0;
}
