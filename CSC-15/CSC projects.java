// Author : Michael Kenny
// Date : 9/4/2020
// Title : Lab 1
// Class : CSC15-05
// Description - First lab testing on escape sequences within string args

public class Lab1{

public static void main(String args[]){

   drawTopSlash(); // Displays a string of top slashes
   writeMessage(); // Displays custom message
   drawBottomSlash(); // Displays a string of backwards slashes
  
}

public static void drawTopSlash(){
  System.out.println("//////////////////////");
}
// No change, displays string

public static void drawBottomSlash(){
  System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
}
// Changed forward slashes to 2x amount of backslashes, displays string

public static void writeMessage(){
  System.out.println("\"  Victory is Near \"");
}
// Changed bars to double quotes, displays string

}