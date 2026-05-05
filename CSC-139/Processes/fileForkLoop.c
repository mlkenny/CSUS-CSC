/*
 * Name: Michael Kenny
 * Date: 9/14/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Processes - fileForkLoop
 * Objective/Goal: Building on the work done with fileFork, What happens when they are writing to the file concurrently, i.e., at the same time?
*/

#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
	int fp;
	char buffer[10];

	pid_t pid;

	if(argc != 2) {
		printf("fileForkLoop: usage <filename>\n");
		return 1;
	}
	
	// Use open system call to open file from first argument.
	// Not 100% sure but seems to open the file at specific file path with permissions.
	// These permissions are the second and third arguments.
	fp = open(argv[1], O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);

	if(fp == -1) {
		printf("fileForkLoop: Error opening file.\n");
		return 1;
	}
	
	// Fork new child process.
	pid = fork();
	
	if(pid != 0) {
		// Parent Process Start

		int i;
		int len;
		// Attempt to write to file with parent.
		if(write(fp, "Parent attempting to write.\n", 28) == -1) {
			printf("fileForkLoop: Error writing to file.\n");
			close(fp);
			return 1;
		}
		// Used wait here to test how wait system call functions between parent and child.
		// Parent halts while child completes, once child terminates parent picks back up after the wait.

		// wait(NULL);
		

		// Write to file 1-5, comparing to process CPU time allocation to child.
		for(i = 1; i <= 5; i++) {
			write(fp, "Parent: ", 8);
			// I found this online as a way to convert integers to a string buffer.
			// Otherwise, how to print integer representation of i is unknown to me.
			len = snprintf(buffer, sizeof(buffer), "%d\n", i);
			write(fp, buffer, len);
		}
	} else {
		// Child Process Start

		int i;
		int len;
		// Attempt to write to file with the child process.
		if(write(fp, "Child attempting to write.\n", 27) == -1) {
			printf("fileForkLoop: Error writing to file.\n");
			close(fp);
			return 1;
		}

		// Write to file 10-15 to differentiate from parent.
		for(i = 10; i <= 15; i++) {
			write(fp, "Child: ", 7);
			len = snprintf(buffer, sizeof(buffer), "%d\n", i);
			write(fp, buffer, len);
		}
		return 0;
	}

	return 0;
}
