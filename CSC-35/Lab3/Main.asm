; MICHAEL KENNY
; CSC 35
; DR. GHANSAH 
; 2/25/2022
; LAB DAY/TIME: THURSDAYS, 12:00PM
; LAB SECTION #05
; PROGRAM #03
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
loc1 DWORD ?
loc2 DWORD ?
loc3 DWORD ?

.code
 
main proc

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
  
  invoke exitprocess,0	;EXIT TO OS
main endp

end main