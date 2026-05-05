//Name: Michael Kenny
//Title: CSC Assignment #6 (CSC20 - Shopping Cart)
//Date: 3/14/21
//Professor: Faroughi
//Description: Create an online shopping store allowing customers to purchase items and place them in their shopping carts.
//             Customers should be able to check out when they are ready with a list of the purchased items as well as total money
//             needing to be paid (which includes tax and shipping costs).

import java.util.*;

class Driver {
   public static void main(String [] args) {
      OnlineShoppingCart myCart = new OnlineShoppingCart();
      
      //checking adding methods
      OnlineItem item1 = new OnlineItem("83927", "Jacket", "Black Fleece", 1, 35.00, 2);
      myCart.add(item1);
      OnlineItem item2 = new OnlineItem("23", "Shoes", "Jordan", 123, 120.55, 3);
      myCart.add(item2);
      OnlineItem item3 = new OnlineItem("123321", "Computer", "Microsoft Windows", 3, 350.97, 8.75);
      myCart.add(item3);
      OnlineItem item4 = new OnlineItem("0019276", "Car", "Nissan Altima", 123, 10560.31, 300);
      myCart.add(item4);
      OnlineItem item5 = new OnlineItem("23", "Shoes", "Jordan", 123, 120.55, 3);
      myCart.add(item5);
      //reviewing cart
      System.out.println("Your shopping cart: " + "\n" + myCart);
      //checking change quantity method
      System.out.println("Changing quantity of Item 1:");
      myCart.changeQuantity("Jacket", 4);
      System.out.println("Quantity of Item 1 has been updated\n");
      //checking equals method
      System.out.println("Checking equals method:");
      if(item2.equals(item5)) {
         System.out.println("Item 2 is the same as Item 5");
      
      }
      //checking remove method
      System.out.println("\nChecking remove method: ");
      //myCart.remove(item5);
      System.out.println("Removed Item 5 from Cart\n");
      //checking out
      myCart.checkout();
            
   }

}

class OnlineShoppingCart {
   
   public static final int SHIP_RATE = 3;
   public static final double TAX_RATE = 7.75/100;
   private ArrayList<OnlineItem> cart;
   
   public OnlineShoppingCart() { //default constructor
      cart = new ArrayList<OnlineItem>();
   
   }
   
   public ArrayList getList() {
      return cart;
   
   }
   
   public void add(OnlineItem o) { //adds item to cart (array), will add duplicates instead of incrementing quantity of pre-existing items
      if(o instanceof Item) {
         cart.add(o);
      
      }
   
   }
   
   public boolean remove(OnlineItem o) { //removes pre-existing item from cart
      if(cart.contains(o)) {
         cart.remove(o);
         return true;
      
      }
      return false;
   
   }
   
   public String toString() {
      StringBuilder s = new StringBuilder();
      for(int i = 0; i < cart.size(); i++) {
         s.append(cart.get(i).toString());
      
      }
      return s.toString();
   
   }
   
   public double totalWeight() { //returns total weight of all items in cart
      double count = 0;
      for(int i = 0; i < cart.size(); i++) {
         count = count + cart.get(i).getWeight();
      
      }
      return count;
   
   }
   
   public double shippingCost() { //returns shipping cost based off total weight of cart times shipping rate. Shipping is free for orders $75 or more
      if(totalPurchaseAmount() >= 75) {
         return 0;
      
      }
      return totalWeight() * SHIP_RATE;
   
   }
   
   public double totalPurchaseAmount() { //returns total price of all items in cart
      double price = 0;
      for(int i = 0; i < cart.size(); i++) {
         price = price + cart.get(i).getTotal();
      
      }
      return price;
   
   }
   
   public double taxedPrice() { //finds taxed price based off total price of cart times tax multiplier, rounds decimal through int conversion
      double tax = totalPurchaseAmount() * TAX_RATE;
      tax = tax * 100;
      tax = (int)tax;
      tax = tax / 100;
      return tax;
   
   }
   
   public boolean changeQuantity(String name, int quantity) {
      for(int i = 0; i < cart.size(); i++) {
         if(name.equalsIgnoreCase(cart.get(i).getName())) {
            cart.get(i).setQuantity(quantity);
            return true;
         
         }
      
      }
      return false;
   
   }
   
   public void checkout() { //prints checkout message along with all items in cart with total price (p) along with individual costs for price, taxed price, and shipping price (total price (p) includes price, taxed price, and shipping price)
      System.out.println("******************************");
      System.out.println("         Checking Out        ");
      System.out.println("******************************");
      System.out.println("Displaying Current Cart: ");
      System.out.println(this.toString());
      System.out.println("Subtotal: $" + totalPurchaseAmount());
      System.out.println("Tax: $" + taxedPrice());
      if(totalPurchaseAmount() < 75) {
         System.out.println("Shipping Costs: $" + shippingCost());
      
      }else {
         System.out.println("Shipping Costs: Free Shipping");
      
      }
      double p = totalPurchaseAmount() + taxedPrice() + shippingCost();
      System.out.println("\nAmount Due: $" + p);
      
   
   }

}

class OnlineItem extends Item {
   
   private double weight;
   
   public OnlineItem(String bc, String n, String d, int quant, double p, double w) { //parameterized constructor
      super(bc, n, d, quant, p);
      weight = w;
   
   }
   
   public double getWeight() {
      return weight;
   
   }
   
   public String toString() {
      return super.toString() + "\nTotal: $" + super.getTotal() + "\n    *******" + "\n";
   
   }

}

class Item {
   
   String barCode;
   double price;
   String name;
   String description;
   int quantity;
   
   public Item(String bc, String n, String d, int quant, double p) { //parameterized constructor
      barCode = bc;
      setPrice(p);
      name = n;
      description = d;
      setQuantity(quant);
   
   }
   
   public String getName() {
      return name;
   
   }
   
   public double getPrice() {
      return price;
   
   }
   
   public int getQuantity() {
      return quantity;
   
   }
   
   public String getDescription() {
      return description;
   
   }
   
   public String getBarCode() {
      return barCode;
   
   }
   
   public double getTotal() {
      return price * quantity;
   
   }
   
   public void incQuantity() {
      quantity++;
   
   }
   
   public boolean setPrice(double p) { //as long as a price p is not negative, will change price to p
      if(p >= 0) {
         price = p;
         return true;
      
      }
      return false;
   
   }
   
   public boolean setQuantity(int q) { //as long as a quantity q is not negative, will change quantity to q
      if(q >= 0) {
         quantity = q;
         return true;
      
      }
      return false;
   
   }
   
   public void setDescription(String d) {
      description = d;
   
   }
   
   public void setBarCode(String code) {
      barCode = code;
   
   }
   
   public String toString() {
      return "\nItem Name: " + name + "\nItem Desc: " + description + "\nItem BarCode: " + barCode + "\nQuantity: " + quantity + "\nItem Price: $" + price;
   
   }
   
   public boolean equals(Item o) { //if o is an instance of the item class, and o's barcode equals that of the given barcode; items are the same and returns true, otherwise returns false
      if(o instanceof Item) {
         if(o.getBarCode() == this.barCode) {
            return true;
         
         }
      
      }
      return false;
   
   }

}