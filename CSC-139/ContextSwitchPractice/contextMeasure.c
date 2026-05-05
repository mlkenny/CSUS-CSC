/*
 * Name: Michael Kenny
 * Date: 9/23/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Context Switch Practice - contextMeasure 
 * Objective/Goal: Create a program that has two processes that can communicate with one another over two pipes. Each process is
 * 		   writing and reading from either pipe and waiting for the other to write before reading. Use this to measure
 * 		   the average time over given iterations.
*/

#define _GNU_SOURCE

#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <sched.h>

#define BUFFER_SIZE 1
#define READ_END 0
#define WRITE_END 1

#define ITERATIONS 1000000

int main() {
	int fd1[2];
	int fd2[2];
	pid_t pid;
	
	if (pipe(fd1) == -1) {
		perror("contextMeasure: Pipe1 creation has failed.\n");
		return 1;
	}

	if(pipe(fd2) == -1) {
		perror("contextMeasure: Pipe2 creation has failed.\n");
		return 1;
	}
	// This forces the process to run on a single core (core 0), which in turn
	// guarantees that both the parent and child process after the fork will run on the
	// same core. This should slow down elapsed average time, but through testing it does
	// not do so. Am I measuring the timer incorrectly?
	cpu_set_t set;
        CPU_ZERO(&set);
        CPU_SET(1, &set);
        sched_setaffinity(getpid(), sizeof(set), &set);

	pid = fork();

	// Timer setup to measure the time taken to use read sys call.
        struct timeval start_time, end_time;
        long long elapsed_microseconds;

	if(pid < 0) {
		// Fork failed.
		perror("contextMeasure: Fork has failed.\n");
		return 1;
	} else if(pid > 0) {
		// Parent Branch
		// Will write to first pipe and read from second pipe.
		close(fd1[READ_END]); // Close first pipe from read end.
		close(fd2[WRITE_END]); // Close second pipe from write end.
		// Gets start time for elapsed section, before for loop since this marks start of communication.         
        	if (gettimeofday(&start_time, NULL) == -1) {
                	perror("contextMeasure: Getting start time has failed.\n");
                	return 1;
        	}
		
		char token = 'x'; // One-byte transfer for communication.

		for(int i = 0; i < ITERATIONS; i++) {
			// Write to fd of first pipe.
			write(fd1[WRITE_END], &token, BUFFER_SIZE);
			read(fd2[READ_END], &token, BUFFER_SIZE);
		}

		// Gets end time for elapsed section, happens after last read call which marks end of communication.
        	if (gettimeofday(&end_time, NULL) == -1) {
                	perror("contextMeasure: Getting end time has failed.\n");
                	return 1;
        	}

		close(fd1[WRITE_END]); // Close first pipe from write end.
		close(fd2[READ_END]); // Close second pipe from read end.

		waitpid(pid, NULL, 0);

		// Calculate the elapsed time in microseconds
        	elapsed_microseconds = (end_time.tv_sec - start_time.tv_sec) * 1000000LL
                             	     + (end_time.tv_usec - start_time.tv_usec);

        	double elapsed_double = (double) elapsed_microseconds / (double) ITERATIONS;
		
		printf("Over the course of %d iterations...\n", ITERATIONS);	
        	printf("Elapsed time per 1 iteration: %.3fus (microseconds)\n", elapsed_double);
	} else if (pid == 0) {
		// Child Branch.
		// Will read from the first pipe and write to the second pipe.
		close(fd1[WRITE_END]); // Close first pipe from write end.
		close(fd2[READ_END]); // Close second pipe from read end.

		char token;

		for(int i = 0; i < ITERATIONS; i++) {
			// Attempt to read from the fd of the first pipe.
			// This call should wait until the fd is written to from the parent (in my case).
			read(fd1[READ_END], &token, BUFFER_SIZE);
			write(fd2[WRITE_END], &token, BUFFER_SIZE);
		}

		close(fd1[READ_END]); // Close first pipe from read end.
		close(fd2[WRITE_END]); // Close second pipe from write end.
		
		return 0;
	}

	return 0;
}
