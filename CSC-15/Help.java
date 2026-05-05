public class Help {
   public static void main(String [] args) {
      int[] list = {0, 0, 2, 0, 4, 0, 6, 0, 8, 0, 10, 0, 12, 0, 14, 0, 16};
      removeInRange(list, 0, 5, 13);
   
   }
   
   public static void removeInRange(int[] list, int element, int start_index, int end_index) {
      for(int i = start_index; i < end_index; i++) {
         if(list[i] == element) {
            list[i] = -1;
         
         }
      
      }
      
   }

}