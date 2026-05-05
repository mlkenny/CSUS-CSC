;CSC35 Lab 8 Symmetric Encryption and Decryption with 4-Byte Key, Spring 2022,
;THIS PROGRAM is 32bit for MASM on MSVC++
; FILL IN THE FOLLOWING WITH APPROPRIATE INFORMATION
;MICHAEL KENNY
;CSC 35
;SPRING
;Instructor: Dr.Ghansah
;LAB DAY/TIME: THURSDAYS, 12:00PM
;LAB SECTION #05
;Program#08 and Title: Symmetric Encryption and Decryption with 4-Byte Key
COMMENT &
Objectives: Students will learn to do simple encryption and decryption using XOR instruction. 
The main program is provided. Students will create specific procedures to integrate with this main program. 
The Skeleton for the procedures are provided. It uses Irvine Libraries for Primitive Input/Output.

&
;

; Irvine Libraries for Readchar, Writechar, WriteString, ClrScr, CrLf Only, Dr. GHANSAH.
;*THIS IS 32 BIT VERSION USING MS VISUAL STUDIO MASM and 32 bit register to hold cryptographic key
;so the Key is 4 bytes. It is used to encrypt/decrypt 4-byte blocks of plaintext
;*********************************************************
; This program asks for secret symmetric shared key from the keyboard. 
;The program then requests the user type a plain text on the keyboard.
; This plain text can be encrypted with the key if the user chooses. 
; In all cases Enter key is pressed to indicate the end of whatever is typed on the keyboard.
; The program displays both plain and encrypted texts if requested. If the user wants to decrypt the text 
;the program asks for the key again before decryption can occur. In all cases user's permission is sought 
;before anything featured is displayed on the Screen. This is a good way to test to see if the program really
;works. For obvious reasons, Every attempt is
; made not to display the cryptographic key as it is typed
; The procedure that collects the key must display a fixed number of astericks instead of the actual key.
; In this case the user should consider the key similar to a password which must be remembered in
; order to get an encrypted text displayed correctly.

; This program uses the following Irvine Library procedures:
;  1) ReadChar for all inputs from the keyboard. Note that ReadChar 
; does not echo characters read from the keyboard. 
; 2) WriteChar is used for output dislays on the screen. 
;If you want to echo characters read from the keyboard you 
;can follow ReadChar immediately with WriteChar. 
;3) WriteString is used primarily for prompting. Prompts are declared in memory and are terminated with 0 (ASCII NULL)
;4) CrLf is used to Create Newlines and 
;5) ClrScr is used for clearing the screen at the beginning.
;No other Irvine Library procedure is used.

.386
.model flat, stdcall
.stack 4096
ExitProcess PROTO ,dwExitCode:DWORD
INCLUDE c:\irvine\Irvine32.inc
include c:\irvine\macros.inc
includelib c:\irvine\irvine32.lib
includelib c:\irvine\kernel32.lib
includelib c:\irvine\user32.lib

;
.data
Heading		BYTE	"*****************************************************",0dh,0ah,
			"*  LAB8 4-BYTE SYMMETRIC KEY CRYPTO TESTING PROGRAM 2022  *",0dh,0ah,
			"*****************************************************",0dh,0ah,0

KeyPrompt	BYTE	"Please Type the secret shared key and press ENTER.",0dh,0ah,
			"The key can be letters and numbers which will ultimately be converted to a number",0dh,0ah,
			"If you use only numbers it should be between 1 and 2^32 - 2, inclusive:",0
PlainPrompt	BYTE	"GetPlainText: Please type your Plaintext message......:",0
PlainNumPrompt	BYTE	"The Number of characters in your message is......:",0
EncryptText	BYTE	"Do you want to Encrypt your PlainText Message? Type N for No or any key for Yes  ",0
EncWrtPrompt	BYTE	"Do you want to Print the Encrypted Text? Type N for No and any key for Yes",0
DecryptPrompt	BYTE	"Do you want to Decrypt the Encrypted Text? Type N for No and any key for Yes",0
TellKeyPrompt	BYTE	"Tell me the key and press ENTER before I decrypt it",0
NoDecptPrompt	BYTE	"The Message cannot be decrypted. Try again or type q to quit",0
Continue	BYTE	"Press any key to Continue or q to quit:",0

;
PlainBuf	BYTE	250 DUP (?)		;Plain Text Buffer
PlainBufCnt	DWORD	?				;Plaintext Buffer Count
EncryptBuf	BYTE	250 DUP (?)		;Encrypted Text Buffer
EncBufCnt	DWORD	?				;Ciphertext Buffer Count
DecryptBuf	BYTE	250 DUP (?)		;Decrypted Text Buffer
Key1		DWORD	?				;Encryption and Decryption Key		
Key2		DWORD 	?
KeySize = TYPE (Key1)				;Key size variable equivalent to Key1 declaration
MaxBuf		DWORD	250				;Maximum buffer size


.code
main proc
    ;

    call clrscr				;Clear the Screen
	    	
    mov edx, offset Heading		;First Large Prompt/Header
    call WriteString
    call crlf

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
topMain: 
   call crlf
   call crlf
    mov edx, offset KeyPrompt	;Prompt for secret Shared key
    call WriteString;
    call GetKey
    mov Key1,EAX
				; Reads Key1 (multiple decimal digits) into EAX. Key1 placed in Key Buffer
    call crlf

    mov edx, offset PlainPrompt		;Prompt for Plaintext
    call WriteString
    								; Gets plaintext and stores in buffer
    call crlf
    mov edx, offset PlainBuf	;WHERE TO STORE THE PLAINTEXT
    mov ecx, MaxBuf				;MAX CHARACTERS IN PLAINTEXT

    call GetPlainText
    mov PlainBufCnt,ecx			;store actual count of PlainText
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    call crlf
    call crlf
    mov edx, offset PlainNumPrompt	;Prompt to Display count of characters
    call WriteString
    mov eax,PlainBufCnt
    call PrintDec				;Print the count in decimal
    call crlf

    mov edx, offset EncryptText		;Prompt Want to encrypt text?
    call WriteString;
    call crlf
    call readchar			;Type 'N' for  No or Any other key for Yes 
    cmp al, 'N' 			;No means type another Plaintext or Quit
    je bottom
    mov esi, offset PlainBuf		; Yes means EncryptText. 
    mov edi, offset EncryptBuf		;esi=ptr to plaintext buffer, edi=ptr to ciphertext buffer
    mov ecx,PlainBufCnt			;ecx=plaintext count in bytes, ax= encryption key
    mov eax,Key1
	mov ebx, KeySize
    call Crypt4Byte			;
    mov EncBufCnt,ecx			;Size of Encrypted data. Needed in case padding was added when n-byte key is used
    call crlf
    mov edx, offset EncWrtPrompt		; Prompt for "Want to Print Encrypted Text? Y or N"
    call WriteString			; print out the number of characters typed????  
    call crlf
    call ReadChar
    cmp al, 'N'				;Type 'N' for  No or Any other key for Yes
    je AskToDecrypt				;If NO Check for Desire to Decrypt
    mov edx, offset EncryptBuf		; YES means Print Encrypted  Text
    mov ecx, PlainBufCnt			;Plain Buffer Count so as not to include any part of the key
    call PrintText
    call crlf
AskToDecrypt:
    mov edx, offset DecryptPrompt
    call WriteString			;Prompt for Do you want to Decrypt Text?
    call crlf				;Type 'N' for  No or Any other key for Yes
    call ReadChar			;Read Response
    cmp al, 'N'				;N means Get Another PlainText
    je bottom
    call crlf
    mov edx, offset TellKeyPrompt	;Yes means tell me the key before I decrypt it
    call WriteString
    call crlf
    call GetKey				;Get the Key
    mov Key2,EAX
    cmp Key1,EAX
			; Reads the Key2, Decrypt Key into AX. Compare with Encrypt Key (Key1) placed 				;in Key1 variable. 
    jne NoDecryption				;If key is not same we cannot decrypt. Otherwise, decrypt
    mov esi, offset EncryptBuf
    mov edi, offset DecryptBuf
    mov ecx, EncBufCnt
    mov eax, Key2
	mov ebx, KeySize
    call Crypt4Byte		; Symmetric Key system. Encyption and Decryption use the same Procedure
			;Both receive things in correct block sizes.

 ;In some implementations
 ; the padding is removed before placing decrypted text in memory.
 ; Regardless, it should return Decrypted text count in ecx	;register. Should Encrypt and Decrypt Procs be the same in
; that case????

    mov edx, offset DecryptBuf		; Print Decrypted Text
    mov ecx, EncBufCnt
    call PrintText
    call crlf
    jmp Bottom
NoDecryption:
    mov edx, offset NoDecptPrompt	;Cannot be Decrypted Statement
    call WriteString
    call crlf
Bottom:
    mov edx, offset continue		;"Press any key to continue or q to quit"
    call WriteString
    call crlf
    call ReadChar
    cmp al,'q'
    jne topMain
    
	invoke exitprocess,0

main endp
   
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;    
GetKey PROC
; get secret shared key from the keyboard in decimal digits. DO NOT ECHO TO SCREEN. BUT PRINT 3 ASTERICKS REGARDLESS OF KEY SIZE.
;Entry: None.
;Exit: Key is placed in EAX
    LOCAL W
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

	ret
GetKey ENDP

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
GetPlainText PROC
; This collects text from keyboard and prints it on the screen? This needs special RdString which 
; echoes to the screen as well as writes to memory.
;
; Read a string of characters from Keyboard (std input) and place it in memory. 
;Stops on ENTER (CR) key.
; Entry: EDX = offset of array of bytes where data is stored
;	ECX = maximum number of characters to be read.
; Exit: count of actual bytes read in ECX
; 
; Note: Stops when user presses ENTER key (CR=0dh)
	call ReadString
    mov ecx, eax

	ret
GetPlainText ENDP

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Crypt4Byte PROC
;This Procedure is used for 4 BYTE Key for Encryption. 
;Consequently, the plaintext must be in 4 Byte Blocks. 
;Therefore, if a plaintext consists of odd number of bytes we should add 1 Byte 'Pad' to the 
;to the Plaintext to force the last byte to be 4 Bytes before we encrypt it. 
;It is good to first determine whether we need add the the pad. 
;This is the case if the plain text size is not divisible by key size
;If we determine we need the pad, we add it to the plaintext in memory before the encryption begins.
;This means that the size of Encrypted text could be different from that of the plaintext.
;However, that should not prevent this procedure to be used for both encryption and decryption.
;NOTE: This procedure can be modified in the future to handle N-byte Encryption where N>4. 
;
;
;Entry: ESI=Pointer to Plaintext in memory. EDI=ptr to encrypted text in memory 
;ECX=count of bytes of plaintext. EAX= symmetric key to be used for encryption or decryption.
;EBX=Key size in Bytes. This allows this Crypt procedure to be more general for any keysize.

;Exit: ECX= actual count of bytes of encrypted text
;
;** LOCAL VARIABLES NEEDED HERE. NOT ENOUGH REGISTERS. NO OTHER LOCAL VARIABLES SHOULD BE CREATED!
.data
KeyLen DWORD ?			;This variable is needed to store ebx, the key size temporarily
KeyBuf DWORD ?			;This variable is needed to store eax, the key, temporarily. eax is used for division
; 
;
.code
    mov KeyLen, ebx
    mov KeyBuf, eax
    push ecx
    mov edx, 0
    mov eax, ecx
    div ebx
    cmp edx, 0
    je Encrypt
    mov ecx, edx
    Start:
        add esi, '0x00'
        inc edx
        pop ecx
        inc ecx
        cmp edx, 4
        je Encrypt
        jmp Start
    Encrypt:
        mov al, esi[eax]

	ret
Crypt4Byte ENDP

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
PrintText PROC
;This procedure is primarily used to print encrypted or decrypted text already in memory on the screen. 
;Entry: EDX=address of string of encrypted text, ECX=byte count of text. Must be >0
    call WriteString
	ret
PrintText ENDP

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
PrintDec PROC
; writes 32-bit unsigned integer to std output in decimal format 
; with no leading zeroes. Can handle multiple decimal digits.
;Can be used to print shared key in decimal if it is a number and
; if requested. Note: Printing shared key is not recommended because the shared key is a secret.
;This procedure can also be used to print the number of characters in a plaintext message or ciphertext. This is its common use. 
;It can be modified to print signed integers, making it more general.
; Entry: EAX = Integer to be printed
; Exit: Nothing
;Note: Can push and pop ax because NO result is returned in AX
	LOCAL count ;create a counting variable to keep track of how long the user input is
	mov count, 0 ;start counter at 0
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

	mov ecx, count ;moves counter reg to current count value (how long user input is)
	Another:
		pop dx ;pops value off of stack
		mov al, dl
		call WriteChar ;writes value
		dec count ;lowers counting variable for each number popped off stack
		cmp count, 0 ;compare if count is 0
		jnz Another ;if count is not 0, then loop continues until all digits are off stack
    
	ret
PrintDec ENDP


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


end main