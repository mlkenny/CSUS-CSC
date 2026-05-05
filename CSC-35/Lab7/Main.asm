; MICHAEL KENNY
; CSC 35
; DR. GHANSAH 
; 4/7/2022
; LAB DAY/TIME: THURSDAYS, 12:00PM
; LAB SECTION #05
; PROGRAM #07
;
.386
.model flat, stdcall
.stack 4096
ExitProcess PROTO ,dwExitCode:DWORD
INCLUDE Irvine32.inc
;This is a comment
;The following is group comment delimited with &
COMMENT &
INCLUDE c:\irvine\Irvine32.inc
include c:\irvine\macros.inc
includelib c:\irvine\irvine32.lib
includelib c:\irvine\kernel32.lib
includelib c:\irvine\user32.lib
&

.data
inputX BYTE "Input X = ", 0
inputY BYTE "Input Y = ", 0
outputW BYTE "Output W = ", 0
calculateW BYTE "Calculating W...", 0
A DWORD 2000
X DWORD 0
Y DWORD 0
W DWORD 0
sum DWORD ?
continue BYTE "Press q to quit or some other key to continue", 0

.code
Input PROC
	mov edx, esi
	call WriteString ;asks user for input X or Y
	mov ebx, 10 ;sets up divisor
	mov ecx, 0 ;sets loop counter to 0 so infinite loop
	Start:
		mov eax, W
		mul ebx
		mov W, eax
		mov eax, 0
		call ReadChar
		call WriteChar
		cmp al, 48 ;compares user input to see if it is a zero
		je Start ;if it is a zero then restart loop (meaning 0 does not affect anything so it is not worthwhile to add it to W)
		cmp al, 0dh ;compares user input to ENTER key
		je Next ;if it is ENTER, jump to Next loop to finalize decimal value
		sub al, 30h ;if user input is anything but 0 or ENTER, convert to ascii and add result to W (temporary place holder)
		movzx eax, al
		add W, eax
		mov eax, 0
		jmp Start ;restarts loop
	
	Next:
		mov edx, 0
		mov eax, W
		mov ecx, 10
		div ecx
		mov ecx, 0
	mov W, 0
	call crlf
	Ret
Input endp

CalcW PROC
	LOCAL loc1
	LOCAL loc2
	LOCAL loc3
	mov loc1, 0
	mov loc2, 0
	mov loc3, 0

	mov edx, offset CalculateW
	call WriteString ;prints calculating W message

	mov eax, 2
	mul esi ;2 * esi = 2 * loc1 = 2 * X ;uses input parameter ESI which is X
	mov loc1, eax ;loc1 = eax = 2 * X
	mov eax, 160
	mul edi ;160 * edi = 160 * loc2 = 160 * Y ;uses input parameter EDI which is Y
	add loc1, eax ;loc1 = loc1 + eax = 160 * Y + 2 * X

	mov eax, A ;A CHANGED FROM LAST LAB, NOW 2000
	mov ebx, 995
	mul ebx
	mov loc2, eax ;loc2 = A * 995 == 2000 * 995

	sub eax, loc1 ;eax = eax - loc1 = loc2 - loc1 = 2000 * 995 - (160 * Y + 2 * X)
	mov loc3, eax ;loc3 = eax

	mov loc2, 1000
	dec loc2

	mov eax, loc2
	add sum, eax
	mov ecx, 8
	div ecx
	add sum, eax
	mov edx, 0
	mov eax, loc2
	mov ecx, 100
	div ecx
	add sum, eax
	mov edx, 0
	mov eax, loc3
	mov ecx, 16
	div ecx
	add sum, eax

	mov edx, 0
	mov eax, sum
	mov ecx, 37199 ;CHANGED FROM LAST LAB
	div ecx
	mov sum, edx
	inc sum

	mov eax, sum
	call crlf
	Ret
CalcW endp

ShowW PROC
	LOCAL count ;create a counting variable to keep track of how long the user input is
	mov count, 0 ;start counter at 0
	mov eax, esi ;moves eax to be paramter value (W)
	mov ebx, 10
	mov ecx, 0
	Start:
		mov edx, 0 ;reset remainder reg
		div ebx ;W / 10
		add dl, 30h ;(W % 10) + 30h, adds 30h to remainder
		push dx ;pushes remainder value to stack
		inc count ;adds 1 to count for every integer
		cmp eax, 0 ;compare if the quotient is equal to 0 (meaning no more digits to go through)
		jne Start ;if eax is not 0 yet, loop again

	mov edx, OFFSET outputW ;points write string fucntion towards outputW string
	call WriteString

	mov ecx, count ;moves counter reg to current count value (how long user input is)
	Another:
		pop dx ;pops value off of stack
		mov al, dl
		call WriteChar ;writes value
		dec count ;lowers counting variable for each number popped off stack
		cmp count, 0 ;compare if count is 0
		jnz Another ;if count is not 0, then loop continues until all digits are off stack
	
	Ret
ShowW endp

main PROC
	mov ecx, 0 ;set counter reg to 0 so infinite loop
	Process:
		mov X, 0 ;reset all variables (this is in case user wants to keep using program with new values for X and Y)
		mov Y, 0
		mov W, 0
		mov sum, 0

		mov esi, offset inputX ;points esi towards X message variable
		call Input ;calls Input procedure to get input from user
		mov X, eax ;moves user's input to X

		mov esi, offset inputY ;points esi towards Y message variable
		call Input ;calls Input procedure again, gets new input from user
		mov Y, eax ;moves user's new input to Y

		mov esi, X ;sets first input paramter to X
		mov edi, Y ;sets first input paramter to Y
		call CalcW
		mov W, eax

		mov esi, W ;sets input paramter to W
		call ShowW ;uses parameter W to print W to screen

		call Crlf ;creates new line for organizational purposes

		mov edx, offset continue ;points edx reg to continue string
		call WriteString
		call ReadChar
		call Crlf
		call WriteChar
		call Crlf ;reads and writes user input for a single character, including some new lines for organization
		cmp al, 113 ;compares user input to ASCII 113 which is lowercase q. If user input is q, then quits process
		jne Process ;if user input is anything but lowercase q, loops again

	INVOKE ExitProcess, 0

main ENDP
END main