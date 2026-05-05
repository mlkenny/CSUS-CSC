class TableHelper {

   public static final int SIZE_OF_TABLE = 10; //class constant for table size
   
   public static void printTable() {
      int factor = 1; //integer variable for factor depending on row
      System.out.println("x  1  2  3  4  5  6  7  8  9  10"); //prints top row which includes x through 10
      for (int i = 1; i <= SIZE_OF_TABLE; i++) { //for loop which creates rows for me based on class constant
         System.out.print(i + "\t"); //prints numbers along with a tab escape sequence
         for (int j = 1; j <= SIZE_OF_TABLE; j++) { //for loop which creates collumns of factors based off the factor variable
            System.out.print(j * factor + "\t");
            
         }
         System.out.println(); //prints new line for a new row
         factor += 1; //increments factor variable by one and reassigns it to that new value
      
      }
   
   }


}