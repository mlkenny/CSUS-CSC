#include <errno.h>      // for errno variable used in perror() messages
#include <stdio.h>      // for printf(), perror()
#include <stdlib.h>     // for exit(), _exit()
#include <sys/time.h>   // for gettimeofday(), struct timeval
#include <sys/types.h>  // for pid_t (used by fork, waitpid)
#include <sys/wait.h>   // for waitpid() to reap the child process
#include <unistd.h>     // for fork(), pipe(), read(), write(), close()

int main(void) {
    int p2c[2];   // pipe for parent → child
    int c2p[2];   // pipe for child → parent
    if (pipe(p2c) == -1) { perror("pipe p2c"); exit(1); }
    if (pipe(c2p) == -1) { perror("pipe c2p"); exit(1); }

    const int iterations = 1000;  // number of ping-pongs
    pid_t pid = fork();
    if (pid < 0) { perror("fork"); exit(1); }

    if (pid == 0) {
        // -----------------------
        // Child process
        // -----------------------
        close(p2c[1]); // child does not write to parent→child pipe
        close(c2p[0]); // child does not read from child→parent pipe

        char token;
        for (int i = 0; i < iterations; i = i + 1) {
            // wait for parent to send a byte
            ssize_t n = read(p2c[0], &token, 1);
            if (n != 1) { perror("child read"); exit(1); }

            // send it back to the parent
            n = write(c2p[1], &token, 1);
            if (n != 1) { perror("child write"); exit(1); }
        }

        close(p2c[0]);
        close(c2p[1]);
        _exit(0); // use _exit() in child to avoid flushing parent's stdio buffers
    } else {
        // -----------------------
        // Parent process
        // -----------------------
        close(p2c[0]); // parent does not read from parent→child pipe
        close(c2p[1]); // parent does not write to child→parent pipe

        char token = 'x';

        struct timeval start, end;
        if (gettimeofday(&start, NULL) == -1) { perror("gettimeofday"); exit(1); }

        for (int i = 0; i < iterations; i = i + 1) {
            // send a byte to the child
            ssize_t n = write(p2c[1], &token, 1);
            if (n != 1) { perror("parent write"); exit(1); }

            // wait for the reply
            n = read(c2p[0], &token, 1);
            if (n != 1) { perror("parent read"); exit(1); }
        }

        if (gettimeofday(&end, NULL) == -1) { perror("gettimeofday"); exit(1); }

        close(p2c[1]);
        close(c2p[0]);
        waitpid(pid, NULL, 0); // reap child process to avoid a zombie

        // compute total elapsed time in microseconds
        long long elapsed_us =
            (end.tv_sec - start.tv_sec) * 1000000LL +
            (end.tv_usec - start.tv_usec);

        // average per-iteration time
        double per_iter_us = (double) elapsed_us / (double) iterations;
        printf("Pipe round-trip (1 byte): %.3f microseconds per iteration\n", per_iter_us);
        printf("Estimated one-way latency: ~%.3f microseconds\n", per_iter_us / 2.0);
    }

    return 0;
}

