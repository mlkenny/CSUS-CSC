/*
 * Name: Michael Kenny
 * Date: 9/14/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Processes - fileFork
 * Objective/Goal: Write a program that opens a file (with the open() system call) and then calls fork() to create a new process.
*/

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
	int fp;
	pid_t pid;

	if(argc != 2) {
		printf("fileFork: usage <filename>\n");
		return 1;
	}
	
	// Invoke open system call to open file based on given argument.
	// Still unsure as to what these parameters do to the open call.
	fp = open(argv[1], O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);

	if(fp == -1) {
		printf("fileFork: Error opening file.\n");
		return 1;
	}
	
	// Create a new child process.
	pid = fork();
	
	if(pid != 0) {
		// Parent Process Start

		// Attempt to write to file with parent.
		//
		// Strings contain a null terminator character at the end.
		// Allowing the write to write up till the len(string) - 1 cuts off
		// this character.
		if(write(fp, "Parent attempting to write.\n", 28) == -1) {
			printf("fileFork: Error writing to file.\n");
			close(fp);
			return 1;
		}
	} else {
		// Child Process Start

		if(write(fp, "Child attempting to write.\n", 27) == -1) {
			printf("fileFork: Error writing to file.\n");
			close(fp);
			return 1;
		}
		// Is this important? I feel as though the child process will need to be
		// killed manually, but I also feel as though the OS will do this automatically
		// once the process terminates.
		
		return 0;
		// Upon testing, the output is identical. Most likely unncessary to exit here.
	}

	return 0;
}
