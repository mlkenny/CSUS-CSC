; MICHAEL KENNY
; CSC 35
; DR. GHANSAH 
; 3/18/2022
; LAB DAY/TIME: THURSDAYS, 12:00PM
; LAB SECTION #05
; PROGRAM #06
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
newline BYTE 0dh, 0ah, 0
loc1 DWORD 0
loc2 DWORD 0
loc3 DWORD 0
sum DWORD ?

.code
Input PROC
	mov loc3, 0
	mov edx, esi
	call WriteString
	mov ebx, 10
	mov ecx, 4
	Top: ;loop allows 4 digit ASCII code to be read from user and uses loc3 to store the value temporarily
		mov eax, loc3 ;sets eax to be current loc1
		mul ebx ;multiply current loc1 (eax) by 10
		mov loc3, eax ;assigns new value to loc1
		mov eax, 0 ;reset eax
		call ReadChar ;ask user for single character input
		call WriteChar ;writes user inputted character for demonstration purposes
		sub al, 30h ;convert ASCII value to digit
		movzx eax, al ;moves new digit to eax (not too sure if movzx is allowed, couldn't find any other way to do it)
		add loc3, eax ;add eax value to pre-existing loc1 value (loc1 will be all user input characters individually)
		mov eax, 0 ;reset eax again
		loop Top ;loops to Top, decrements ecx (loop counter)

	mov eax, loc3 ;moves eax to be loc3 since output variable is eax
	call crlf ;creates new line
	Ret
Input endp

CalcW PROC
	mov edx, offset CalculateW
	call WriteString

	mov eax, 2
	mul esi ;2 * esi = 2 * loc1 = 2 * X
	mov loc1, eax ;loc1 = eax = 2 * X
	mov eax, 160
	mul edi ;160 * edi = 160 * loc2 = 160 * Y
	add loc1, eax ;loc1 = loc1 + eax = 160 * Y + 2 * X

	mov eax, 200
	mov ebx, 995
	mul ebx
	mov loc2, eax ;loc2 = 200 * 995

	sub eax, loc1 ;eax = eax - loc1 = loc2 - loc1 = 200 * 995 - (160 * Y + 2 * X)
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
	mov ecx, 9
	div ecx
	mov sum, edx
	inc sum

	mov eax, sum
	call crlf
	Ret
CalcW endp

ShowW PROC
	mov eax, esi
	add al, 30h ;al = al + 30h = 5 + 30h = 53h = 5 in ASCII (al is 5 in this case since it is the lower byte [only byte] of the ax register)
	mov edx, OFFSET outputW ;points write string fucntion towards outputW string
	call WriteString
	call WriteChar ;prints al
	
	Ret
ShowW endp

main PROC
	mov esi, offset inputX ;points esi towards X message variable
	call Input ;calls Input procedure to get input from user
	mov loc1, eax ;moves user's input to loc1

	mov esi, offset inputY;points esi towards Y message variable
	call Input ;calls Input procedure again, gets new input from user
	mov loc2, eax ;moves user's new input to loc2

	mov esi, loc1 ;sets up first parameter to be first user input
	mov edi, loc2 ;sets up second parameter to be second user input
	call CalcW ;calls CalcW procedure to calculate W based on user's input
	mov sum, eax ;moves calculated value of W to be in "sum" variable

	mov esi, sum ;passes W through to ShowW procedure
	call ShowW ;calls ShowW procedure and prints W to screen, no return values necessary

	INVOKE ExitProcess, 0

main ENDP
END main