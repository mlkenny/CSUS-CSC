/*
 * Name: Michael Kenny
 * Date: 9/14/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Processes - pipeFork
 * Objective/Goal: Write a program that creates two children, and connects the standard output of one
 * 		   to the standard input of the other, using the pipe() system call.
*/

#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define BUFFER_SIZE 25
#define READ_END 0
#define WRITE_END 1

int main() {
	// Using similar ideology as the textbook with a buffer for read and write.
	// Also naming the file descriptor array to allow for manipulation via our constants.
	char write_msg[BUFFER_SIZE] = "Greetings";
	char read_msg[BUFFER_SIZE];
	int fd[2]; // fd[0] == read, fd[1] == write

	if(pipe(fd) == -1) {
		printf("pipeFork: Pipe has failed\n");
		return 1;
	}
	
	// Start first child process.
	pid_t child1_pid = fork();

	if(child1_pid == 0) {
		printf("pipeFork: Attempting to write to pipe\n");
		// Child1 is the writing process.
		// Good practice is to close the opposite end of the pipe,
		// then use the leftover end.
                close(fd[READ_END]); // Close opposite end.
                write(fd[WRITE_END], write_msg, strlen(write_msg)+1); // Write to pipe from buffer.
                close(fd[WRITE_END]); // Close rest of pipe.
		return 0;
	} else if(child1_pid < 0) {
		printf("pipeFork: Fork has failed in child 1\n");
		return 1;
	}
	
	// Start second child process after first terminates.
	pid_t child2_pid = fork();
	
	if(child2_pid == 0) {
		printf("pipeFork: Attempting to read from pipe\n");
		// Child2 is the reading process.
		// Once the first child terminates the second will use the same pipe to access the read end.
                close(fd[WRITE_END]);
                read(fd[READ_END], read_msg, BUFFER_SIZE); // Read from pipe into a different buffer.
                printf("Child process reads: %s\n", read_msg);
                close(fd[READ_END]); // Close pipe.
	} else if(child2_pid < 0) {
                printf("pipeFork: Fork has failed in child 2\n");
                return 1;
        }

	return 0;
}
