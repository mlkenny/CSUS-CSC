public class Hokeemon {

   private String name;
   private String type;
   private int age;
   
   public String toString() {
      return "\n" + "Name " + name + ": Type " + type + ": Age " + age;
   
   }
   
   public String livesIn() { //determines place of residence based on type
      if(type.equals("Dwarf")) {
         return "Mountains";
      
      }else if(type.equals("Hobbit")) {
         return "Shire";
      
      }else if(type.equals("Elf")) {
         return "Dale";
      
      }else {
         return "Forest";
      
      }
   
   }
   
   public String getName() {
      return name;
   
   }
   
   public int getAge() {
      return age;
   
   }
   
   public String getType() {
      return type;
   
   }
   
   public Hokeemon(String name, String type, int age) {
      this.name = name;
      this.type = type;
      this.age = age;
   
   }
   
   public boolean areFriends(Hokeemon other) { //determines if creatures of certain types can be friends or not
      if(this.getType().equals("Dwarf") && other.getType().equals("Elf")) {
         return true;
      
      }else if(this.getType().equals("Hobbit") && other.getType().equals("Fairy")) {
         return true;
      
      }else if(this.getType().equals("Elf") && other.getType().equals("Dwarf")) {
         return true;
      
      }else if(this.getType().equals("Fairy") && other.getType().equals("Hobbit")) {
         return true;
      
      }else {
         return false;
      
      }
   
   } 

}