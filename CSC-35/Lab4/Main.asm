; MICHAEL KENNY
; CSC 35
; DR. GHANSAH 
; 3/4/2022
; LAB DAY/TIME: THURSDAYS, 12:00PM
; LAB SECTION #05
; PROGRAM #04
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
A DWORD 200
X DWORD 40
Y DWORD 24
W DWORD ?
loc1 DWORD ?
loc2 DWORD ?
loc3 DWORD ?
sum DWORD ?

.code

main PROC
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

	mov edx, 0
	mov eax, sum
	mov ecx, 9
	div ecx ;returns sum/9 to eax and remainder to edx
	mov eax, edx ;eax = edx = sum%9
	inc eax ;increment eax, eax = eax + 1 = sum%9 + 1 == 5

	add al, 30h ;al = al + 30h = 5 + 30h = 53h = 5 in ASCII (al is 5 in this case since it is the lower byte [only byte] of the ax register)
	call WriteChar ;prints al

	INVOKE ExitProcess, 0

main ENDP
END main