//Name: Michael Kenny
//Title: CSC Assignment #7 (CSC20 - Movie List)
//Date: 4/4/21
//Professor: Faroughi
//Description: Create a movie list for a movie theater that implements classes (Movie, ListNode, MovieList, List, and a Driver).
//             Program should allow user to display list of movies with corresponding title, genre, rating, and number of viewers watched.
//             Methods should also be implemented in order to compare movies, list the most watched/highest rated movie(s), add and remove new movies
//             to and from the list, change a movie's position in an existing list, and return which position a given movie is in.
import java.util.*;

public class Movie {
   String title;
   String genre;
   int rating;
   int viewers;
   
   public Movie() { //default constructor
      
   
   }
   
   public Movie(String t, String g, int r, int v) { //parameterized constructor
      title = t;
      genre = g;
      rating = r;
      viewers = v;
   
   }
   
   public String getTitle() {
      return title;
   
   }
   
   public String getGenre() {
      return genre;
   
   }
   
   public int getRating() {
      return rating;
   
   }
   
   public int getViewers() {
      return viewers;
   
   }
   
   public void setTitle(String s) {
      title = s;
   
   }
   
   public void setGenre(String s) {
      genre = s;
   
   }
   
   public void setRating(int n) {
      rating = n;
   
   }
   
   public void setViewers(int n) {
      viewers = n;
   
   }
   
   public boolean compareTo(Movie o) {
      if(this.title == o.getTitle()) {
         return true;
      
      }
      return false;
   
   }
   
   public String toString() {
      return title + ", " + genre + ", " + rating + ", " + viewers;
   
   }

}

class ListNode {
   private Movie mov;
   private ListNode next;
   
   public ListNode() { //default constructor
      
   
   }
   
   public ListNode(Movie m, ListNode n) { //parameterized constructor for both movie and list node
      mov = m;
      next = n;
   
   }
   
   public ListNode(Movie m) { //parameterized constructor for movie
      mov = m;
   
   }
   
   public Movie getMovie() {
      return mov;
   
   }
   
   public ListNode getNext() {
      return next;
   
   }
   
   public void setNext(ListNode n) {
      next = n;
   
   }

}

interface List {
   LinkedList<Movie> linky = new LinkedList<Movie>(); //creates linked list with type movie object
   
   public void add(String title, String genre, int rating, int viewers);
   
   public void add(int index, String title, String genre, int rating, int viewers);
   
   public int indexOf(String movieName);
   
   public void remove(String movieName);
   
   public int size();
   
   public String toString();
   
   public Movie get(int pos);

}

class MovieList extends Movie implements List { //implements list interface with linky list
   private ListNode front;
   public static int size = 0;
   
   public MovieList() { //default constructor
      
   
   }
   
   public void add(String title, String genre, int rating, int viewers) { //add to linky list
      Movie temp = new Movie(title, genre, rating, viewers);
      linky.add(temp);
   
   }
   
   public void add(int index, String title, String genre, int rating, int viewers) { //add to linky list at specific index
      Movie temp = new Movie(title, genre, rating, viewers);
      linky.add(index - 1, temp);
   
   }
   
   public int indexOf(String movieName) { //return position (NOT INDEX) of movie
      int index = 0;
      for(int i = 0; i < linky.size(); i++) {
         Movie temp = linky.get(i);
         if(temp.getTitle().equalsIgnoreCase(movieName)) {
            index = i;
         
         }
      
      }
      return index + 1; //(added 1 to account for position)
   
   }
   
   public void remove(String movieName) { //removes movie from linky if equals specified movieName
      for(int i = 0; i < linky.size(); i++) {
         Movie first = linky.get(i);
         if(first.getTitle().equalsIgnoreCase(movieName)) {
            linky.remove(first);
         
         }
      
      }
   
   }
   
   public int size() {
      return linky.size();
   
   }
   
   public String toString() {
      String s = "";
      for(int i = 0; i < linky.size(); i++) {
         s = s + linky.get(i).toString() + "\n";
      
      }
      return s;
   
   }
   
   public Movie get(int pos) {
      return (Movie)linky.get(pos);
   
   }
   
   public String getMovie(int star) {
      LinkedList<Movie> l = new LinkedList<Movie>();
      for(int i = 0; i < linky.size(); i++) {
         Movie temp = (Movie)linky.get(i);
         if(temp.getRating() == star) {
            l.add(temp);
         
         }
      
      }
      return l.toString();
   
   }
   
   public Movie mostWatched() {
      int var = 0; //tracks max viewer count
      int index = 0; //tracks highest viewer count's (some movie's) index
      for(int i = 0; i < linky.size(); i++) {
         Movie first = linky.get(i);
         if(first.getViewers() > var) {
            var = first.getViewers();
            index = i;
         
         }
      
      }
      return linky.get(index); //returns (some movie's) index
   
   }

}

class Driver {
   public static void main(String [] args) {
      MovieList list = new MovieList();
      
      list.add("Sunny Day", "Action", 5, 20000);
      list.add("Airplane", "Comedy", 3, 1200);
      list.add("Doctor Zhivago", "Comedy", 4, 23000);
      list.add("The Deer Hunter", "Family", 3, 2345);
      System.out.println("Here is the list of the movies\n");
      System.out.println(list);
      System.out.println("\nHere is the movie that was the most watched");
      System.out.println(list.mostWatched());
      System.out.println("\nHere is the list of 5 star ratings");
      System.out.println(list.getMovie(5));
      System.out.println("\nRemoving Airplane Movie...");
      list.remove("Airplane");
      System.out.println(list);
      System.out.println("Displaying the second movie in the list");
      System.out.println(list.get(1));
      System.out.println("\nAdding a new movie (Up) at position 2");
      list.add(2, "Up", "Cartoon", 3, 4500);
      System.out.println(list);
      int i = list.indexOf("Up");
      System.out.println("The movie Up is in the position " + i);
   
   }

}