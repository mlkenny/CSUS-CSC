// Name: Michael Kenny
// Class: CSC15-10
// Date: 9/18/2020
// Title: Lab 3

public class CoinConverKenny{
   public static void main(String [] args){
      helperMethod();
      System.out.println();
      anotherHelperMethod();
   
   }
   public static void helperMethod(){
      int pennyCount = 100, nickelCount = 44, dimeCount = 20, quarterCount = 200;
      int totalCents = pennyCount + nickelCount * 5 + dimeCount * 10 + quarterCount * 25; // gets total amount of money in cents (5520)
      double totalMoney = (double)totalCents / 100; // stores dollars and cents, whole number = dollars, decimals = cents (55.2)
      int dollars = (int)totalMoney; // turns total money in dollars and cents into just dollars (55)
      double cents = (totalMoney - dollars)*100; // returns cents back by subtracting dollar amount from total money (52)
      double feeCalc = (totalCents * .1)/100; // calculates amount taxed (5.52)
      double feeAmntCents = Math.round((feeCalc-(int)feeCalc)*100); // calculates only cents in the taxed amount (52)
      double resultMoney = totalMoney - feeCalc; // finds money leftover after tax (49.68)
      int resultMoneyDollars = (int)resultMoney; // finds only dollars within leftover money (49)
      double resultMoneyCents = (resultMoney - resultMoneyDollars)*100; // finds only cents within leftover money (68)
      System.out.println(quarterCount+" quarters, "+dimeCount+" dimes, "+nickelCount+" nickels, "+pennyCount+" pennies, is equal to: " +totalCents+ " cents.");
      System.out.println("In dollars that is: $"+dollars+" and "+(int)cents+" cents.");
      System.out.println("The fee you will pay is: "+(int)feeCalc+" dollars and "+ feeAmntCents+" cents.");
      System.out.println("You should get $"+resultMoneyDollars+" and "+ (int)resultMoneyCents + " cents back.");
   
   }
   public static void anotherHelperMethod(){
      int pennyCount = 200, nickelCount = 55, dimeCount = 45, quarterCount = 350;
      int totalCents = pennyCount + nickelCount * 5 + dimeCount * 10 + quarterCount * 25; // gets total amount of money in cents (9675)
      double totalMoney = (double)totalCents / 100; // stores dollars and cents, whole number = dollars, decimals = cents (96.75)
      int dollars = (int)totalMoney; // turns total money in dollars and cents into just dollars (96)
      double cents = (totalMoney - dollars)*100; // returns cents back by subtracting dollar amount from total money (75)
      double feeCalc = (totalCents * .1)/100; // calculates amount taxed (9.68)
      double feeAmntCents = Math.round((feeCalc-(int)feeCalc)*100); // calculates only cents in the taxed amount (68)
      double resultMoney = totalMoney - feeCalc; // finds money leftover after tax (87.7)
      int resultMoneyDollars = (int)resultMoney; // finds only dollars within leftover money (87)
      double resultMoneyCents = (resultMoney - resultMoneyDollars)*100; // finds only cents within leftover money (7)
      System.out.println(quarterCount+" quarters, "+dimeCount+" dimes, "+nickelCount+" nickels, "+pennyCount+" pennies, is equal to: " +totalCents+ " cents.");
      System.out.println("In dollars that is: $"+dollars+" and "+(int)cents+" cents.");
      System.out.println("The fee you will pay is: "+(int)feeCalc+" dollars and "+ feeAmntCents+" cents.");
      System.out.println("You should get $"+resultMoneyDollars+" and "+ (int)resultMoneyCents + " cents back.");
   
   }

}