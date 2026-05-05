# Michael Kenny

lab10: lab10.o ParseCmd.o ProcessExtCmd.o Redirect.o RunExtCmd.o
	gcc lab10.o ParseCmd.o ProcessExtCmd.o RunExtCmd.o Redirect.o -o lab10

lab10.o: lab10.c lab9_10.h
	gcc -c lab10.c -lm -Wall

ParseCmd.o: ParseCmd.c lab9_10.h
	gcc -c ParseCmd.c -lm -Wall

ProcessExtCmd.o: ProcessExtCmd.c lab9_10.h
	gcc -c ProcessExtCmd.c -lm -Wall

Redirect.o: Redirect.c lab9_10.h
	gcc -c Redirect.c -lm -Wall

RunExtCmd.o: RunExtCmd.c lab9_10.h
	gcc -c RunExtCmd.c -lm -Wall
