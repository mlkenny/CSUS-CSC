;THIS PROGRAM Lab2 Hello_Neg It is 32bit for MASM on MSVC++
; FILL IN THE FOLLOWING WITH APPROPRIATE INFORMATION
;MICHAEL KENNY
;CSC 35
;SPRING
;Instructor: Dr.Ghansah
;WED, 2/16/2022, 11:19
;LAB SECTION #05
;Program#2 Negative and Positive Numbers
COMMENT &
Objectives: Students will learn to output to screen using Libraries and Deal with Negative Numbers

&
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
msg1 BYTE "Hello CSC35!", 0dh, 0ah, 0
msg2 BYTE "My Name is Michael Kenny!", 0dh, 0ah, 0

.code
 
main proc
;
;output the Hello Message on the Screen
;
  mov edx, OFFSET msg1
  call writestring
  mov edx, OFFSET msg2
  call writestring
;
;*******************************
; Negative and Positive numbers in Binary
;
  mov eax, 0fffffffh ;makes eax equal to -1, in other terms: eax == -1
  add eax, 1 ;adds positive 1 to eax, in math terms: eax + 1 = 128
  neg eax ;causes eax to turn negative. same as multiplying by -1: eax = -128
  add eax, 20000001h ;adds one to eax, math: eax + 20000001h = 129
  mov bx, -1 ;sets bx equal to -1, in math terms: bx == -1
  add ax, bx ;adds ax and bx together, math: bx + ax = 128
  
  invoke exitprocess,0	;EXIT TO OS
main endp

end main
INCLUDE Irvine32.inc

.386
.model flat, stdcall
.stack 4096
ExitProcess PROTO, deExitCode:DWORD

.data

.code
main PROC
	INVOKE ExitProcess, 0

main ENDP