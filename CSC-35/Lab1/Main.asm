;THIS PROGRAM Lab1 Familiarizarion It is 32bit for MASM on MSVC++
; FILL IN THE FOLLOWING WITH APPROPRIATE INFORMATION
;MICHAEL KENNY
;CSC 35
;SPRING
;Instructor: Dr.Ghansah
;WEDNESDAY, 2/9/2022 and 6:09PM
;LAB SECTION #05

INCLUDE Irvine32.inc
include c:\irvine\macros.inc
includelib c:\irvine\irvine32.lib
includelib c:\irvine\kernel32.lib
includelib c:\irvine\user32.lib

.386
.model flat, stdcall
.stack 4096
ExitProcess PROTO, deExitCode:DWORD

.data
Result DWORD ?

.code
main PROC
  mov ebx,2		;ebx=2
  inc ebx		;ebx++ i.e. ebx=ebx+1
  add ecx,2		;ecx=ecx+2
  mov eax,ebx	;eax=eax+ebx  
  add eax,ecx	; eax = eax+ecx
  mov ebx,eax	;ebx=eax
  mov eax,ebx
  dec ebx
  sub eax,ecx		; eax=eax-ecx
  add eax,ebx	;eax=eax+ebx
  mov Result,eax	;Result= eax
	INVOKE ExitProcess, 0

main ENDP
END main