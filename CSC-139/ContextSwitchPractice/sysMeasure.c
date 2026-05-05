/*
 * Name: Michael Kenny
 * Date: 9/23/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: Context Switch Practice - sysMeasure
 * Objective/Goal: Create a program that uses the gettimeofday() to calculate the average time cost of a null system call.
*/

#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/time.h>

#define ITERATIONS 100000

int main() {
	/* I realized after the fact that files were completely unncessary for this assignment. There is no need to store
	 * the information that I am writing or reading to a file if I am just piping them back and forth within the processes.
	 * But this works, so hey I'm gonna leave it be.
	*/
	int fd;
	
	// This creates the read file if it does not exist.
    	fd = open("read_file.txt", O_CREAT | O_WRONLY | O_TRUNC, 0644);
    	if (fd == -1) {
        	perror("sysmeasure: Error creating test file.\n");
        	return 1;
    	}
    	close(fd);
	
	// Open the test reading file.
    	fd = open("read_file.txt", O_RDONLY);
    	if (fd == -1) {
       		perror("sysmeasure: Error reading test file.\n");
        	return 1;
    	}
	
	// Timer setup to measure the time taken to use read sys call.
	struct timeval start_time, end_time;
    	long long elapsed_microseconds;
	
	// Gets start time for elapsed section.    	
	if (gettimeofday(&start_time, NULL) == -1) {
        	perror("sysmeasure: Getting start time has failed.\n");
        	return 1;
    	}	
	// Do work here between start time and end time.
	for(int i = 0; i < ITERATIONS; i++) {
		char buffer[1]; // A buffer is still required, even if no bytes are read
		read(fd, buffer, 0);
	}
	
	// Gets end time for elapsed section.
    	if (gettimeofday(&end_time, NULL) == -1) {
        	perror("sysmeasure: Getting end time has failed.\n");
        	return 1;
    	}
	close(fd);

    	// Calculate the elapsed time in microseconds
    	elapsed_microseconds = (end_time.tv_sec - start_time.tv_sec) * 1000000LL
			     + (end_time.tv_usec - start_time.tv_usec);
	
	double elapsed_double = (double) elapsed_microseconds / (double) ITERATIONS;
	printf("Over the course of %d iterations...\n", ITERATIONS);	
	printf("Elapsed time per 1 iteration: %.6fus (microseconds)\n", elapsed_double);

	return 0;
}
