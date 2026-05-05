all: fork fileFork fileForkLoop helloFork execFork waitFork waitPIDFork closeFork pipeFork

clean:
	rm -f fork fileFork fileForkLoop helloFork execFork waitFork waitPIDFork closeFork pipeFork

fork: fork.c
	gcc -o fork fork.c -Wall -Werror

fileFork: fileFork.c
	gcc -o fileFork fileFork.c -Wall -Werror

fileForkLoop: fileForkLoop.c
	gcc -o fileForkLoop fileForkLoop.c -Wall -Werror

helloFork: helloFork.c
	gcc -o helloFork helloFork.c -Wall -Werror

execFork: execFork.c
	gcc -o execFork execFork.c -Wall -Werror

waitFork: waitFork.c
	gcc -o waitFork waitFork.c -Wall -Werror

waitPIDFork: waitPIDFork.c
	gcc -o waitPIDFork waitPIDFork.c -Wall -Werror

closeFork: closeFork.c
	gcc -o closeFork closeFork.c -Wall -Werror

pipeFork: pipeFork.c
	gcc -o pipeFork pipeFork.c -Wall -Werror
