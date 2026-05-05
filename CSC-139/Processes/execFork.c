/*
 * Name: Michael Kenny
 * Date: 9/14/2025
 * Course: CSC-139-05
 * Prof: Dr. Khaleq
 * Assignment: processes - execFork
 * Objective/Goal: Write a program that calls fork() and then calls some form of exec() to run the program /bin/ls.
 * 		   See if you can try all of the variants of exec(), including (on Linux) execl(), execle(), execlp(), execv(), execvp(), and execvpe().
*/

#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
	if(argc != 2) {
		printf("execFork: usage <execl, execle, execlp, execv, execvp, execvpe>\n");
		return 1;
	}
	
	// Start new child process.
	fork();

	if(strcmp(argv[1], "execl") == 0) {
		// execl
		// Takes in a path, a file name, as well as arguments for permissions.
		// File must exist in path directory or will fail.
		// Must end in a NULL termination.
		execl("/bin/ls", "ls", "-l", NULL);
		printf("execFork: Failed for execl\n"); // The code should never reach this point if the exec functions correctly.
		return 1; // Returning 1 means there was some kind of error executing a the file location.
	} else if(strcmp(argv[1], "execle") == 0) {
		// execle
		// Takes in a path with a file as well, but can also pass in environment variables
		// to be used in the new process. In this case I passed a variable holding a text string,
		// this must end in a NULL termination as well (some sort of pattern it seems).
		char *env[2] = {"var=Hello World", NULL};
		execle("/bin/ls", "ls", "-l", NULL, env);
		printf("execFork: Failed for execle\n"); // Again, this should never be reached unless exec fails.
		return 1;
	} else if(strcmp(argv[1], "execlp") == 0) {
		// execlp
		// Takes in similar arguments as execl,
		// but does not require a specific path to the directory.
		// execlp will search all directories in a given path for the file name.
		execlp("/bin/ls", "ls", "-l", NULL);
                printf("execFork: Failed for execlp\n");
		return 1;
        } else if(strcmp(argv[1], "execv") == 0) {
		// execv
		// Takes in a path to a directory as well as an arugments array.
		// This array contains the file name, permissions arguments, and NULL termination.
		// Does not read through directories, hence it requires the absolute path to the executable for the new process.
		// This exec does not specify a new environment (variables) for the process,
		// but instead carries over the existing ones from this process to the next (inheritance).
		char *const args[] = {"ls", "-l", NULL};
                execv("/bin/ls", args);
		printf("execFork: Failed for execv\n");
		return 1;
        } else if(strcmp(argv[1], "execvp") == 0) {
		// execvp
		// Takes in similar arguments as execv, but does not require an absolute path.
		// The executable can exist anywhere in the given directory.
		char *const args[] = {"ls", "-l", NULL};
                execvp("/bin/ls", args);
		printf("execFork: Failed for execvp\n");
		return 1;
        } else if(strcmp(argv[1], "execvpe") == 0) {
		// execvpe?
		// I was not able to find a way to use this system call, it kept popping up as something
		// that did not exist after attempting to compile the code.
		// According to man page this call functions simiarly to execvp but requires setting up a custom environment
		// like in execvp, execv, and execle.

		// I would set it up like this, similarly to the others.
		/*
		char *const args[] = {"ls", "-l", NULL};
		char *env[2] = {"var=Hello World", NULL};
                execvpe("/bin/ls", args, env);
		printf("execFork: Failed for execvpe\n");
		return 1;
		*/
        } else {
		// If none of the previous cases are caught, it is not a valid exec system call.
		printf("execFork: invalid argument\n");
		return 1;
	}

	return 0;

}
