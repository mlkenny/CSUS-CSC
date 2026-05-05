/*
 * Name: Michael Kenny
 * Date: 9/14/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Processes - closeFork
 * Objective/Goal: Write a program that creates a child process, and then in the child closes standard output (STDOUT FILENO).
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
	pid_t pid;
	pid_t child_pid;

	// Create child process.
	pid = fork();

	if(pid == 0) {
		// Child Process
		printf("closeFork: Child process closing STDOUT_FILENO...\n");
		// Using system call close to clear the default file descriptor.
		close(STDOUT_FILENO);
		printf("closeFork: Attempting to print\n");
		return 0;
	} else if (pid > 0) {
		// Parent Process
		child_pid = wait(NULL);
		// Once child finishes print out child pid and return.
		printf("closeFork: Child return pid of: %d\n", child_pid);
		return 0;
	} else {
		// Fork failed
	}

	return 0;
}
