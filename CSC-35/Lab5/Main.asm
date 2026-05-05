; MICHAEL KENNY
; CSC 35
; DR. GHANSAH 
; 3/10/2022
; LAB DAY/TIME: THURSDAYS, 12:00PM
; LAB SECTION #05
; PROGRAM #05
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
newline BYTE 0dh, 0ah, 0
A DWORD 200
X DWORD 0
Y DWORD 0
W DWORD ?
loc1 DWORD ?
loc2 DWORD ?
loc3 DWORD ?
sum DWORD ?

.code
main PROC
	;This chunk of code asks user for 4 digit unsigned decimal numbers and equates it to X

	mov edx, OFFSET inputX ;points write string function towards inputX string
	call WriteString ;writes edx, edx = inputX string
	mov ebx, 10 ;set ebx as a constant multiplier
	mov ecx, 4 ;set ecx (loop counter) to be 4
	Top:
		mov eax, X ;sets eax to be current X
		mul ebx ;multiply current X (eax) by 10
		mov X, eax ;assigns new value to X
		mov eax, 0 ;reset eax
		call ReadChar ;ask user for single character input
		call WriteChar ;writes user inputted character for demonstration purposes
		sub al, 30h ;convert ASCII value to digit
		movzx eax, al ;moves new digit to eax (not too sure if movzx is allowed, couldn't find any other way to do it)
		add X, eax ;add eax value to pre-existing X value (X will be all user input characters individually)
		mov eax, 0 ;reset eax again
		loop Top ;loops to Top, decrements ecx (loop counter)

	mov edx, OFFSET newline ;points write string function towards newline string
	call WriteString ;writes newline, creates a gap

	;This chunk of code asks user for 4 digit unsigned decimal numbers and equates it to Y
	mov edx, OFFSET inputY ;same methodology and logic as inputX
	call WriteString
	mov eax, Y
	mov ecx, 4
	Next:
		mov eax, Y
		mul ebx
		mov Y, eax
		mov eax, 0
		call ReadChar
		call WriteChar
		sub al, 30h
		movzx eax, al
		add Y, eax
		mov eax, 0
		loop Next

	mov edx, OFFSET newline
	call WriteString
	
	;This chunk of code solves for loc3
	mov eax, 2 ;eax = 2
	mul X ;eax = X * 2
	mov ebx, eax ;ebx = X * 2
	mov eax, 160 ;eax = 160
	mul Y ;eax = Y * 160
	add eax, ebx ;eax = (X * 2) + (Y * 160)
	mov loc1, eax ;loc1 = eax = (X * 2) + (Y * 160)
	mov eax, 995 ;eax = 995
	mul A ;eax = A * 995
	mov loc2, eax ;loc2 = eax = A * 955
	sub eax, loc1 ;eax = eax - loc1
	mov loc3, eax ;loc3 = eax

	;This chunk of code solves for sum
	mov Y, 1000 ;Y = 1000
	dec Y ;Y = Y - 1 = 999
	mov eax, Y ;eax = Y = 999
	add sum, eax ;sum = sum + eax = Y
	mov eax, loc3 ;eax = loc3
	mov ecx, 16
	div ecx ;returns loc3/16 to eax and remainder to edx
	add sum, eax ;sum = sum + eax = Y + loc3/16
	mov edx, 0
	mov eax, Y
	mov ecx, 8
	div ecx ;returns Y/8 to eax and remainder to edx
	add sum, eax ;sum = sum + eax = Y + loc3/16 + Y/8
	mov edx, 0
	mov eax, Y
	mov ecx, 100
	div ecx ;returns Y/100 to eax and remainder to edx
	add sum, eax ;sum = sum + eax = Y + loc3/16 + Y/8 + Y/100

	;This chunk of code solves for sum MOD 9
	mov edx, 0
	mov eax, sum
	mov ecx, 9
	div ecx ;returns sum/9 to eax and remainder to edx
	mov eax, edx ;eax = edx = sum%9
	inc eax ;increment eax, eax = eax + 1 = sum%9 + 1 == 5

	;This chunk of code prints value of al to screen
	add al, 30h ;al = al + 30h = 5 + 30h = 53h = 5 in ASCII (al is 5 in this case since it is the lower byte [only byte] of the ax register)
	mov edx, OFFSET outputW ;points write string fucntion towards outputW string
	call WriteString
	call WriteChar ;prints al

	INVOKE ExitProcess, 0

main ENDP
END main