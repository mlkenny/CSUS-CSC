import java.util.ArrayList;

class Person
{
   private String name;
   private String last;
   public Person(String name, String last)
   {
     this.name = name;
     this.last = last;
   }
   public String getLast()
   {
     return last;
   }
   public String getFirst()
   {
     return name;
   }
   public String toString()
   {
     return name + " " + last;
   }
}

public class ArrayList
{
  public static void main(String[] args)
  {
    ArrayList<Person> list = new ArrayList<Person>();
     list.add(new Person ("alex","Bus"));
     list.add(new Person("Mary", "Phillips"));
     list.add(new Person("Nik", "Lambard") );
     list.add(new Person("Rose","Rodd"));
     list.add(new Person("Esa","khan"));
     list.add(new Person("Jose","Martinex"));
     list.add(new Person("Nik","Patte"));
     System.out.println(list);
     push(list);
     System.out.println(list);

  
  }
//this method pushes all the people with the even length last name to the end of the list
 public static void push(ArrayList list) {
   for(int i = 0; i < list.size(); i++) {
      list.get(i).getLast();
   
   }
         
 }
     
   
}