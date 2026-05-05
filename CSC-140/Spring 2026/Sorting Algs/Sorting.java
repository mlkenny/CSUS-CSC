import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sorting {
	final int MAX_SIZE = 10000000;

	// Set this to true if you wish the arrays to be printed.
	final static boolean OUTPUT_DATA = true;
	
	public static String sortAlg= null;
	public static int size = 0;
	public static int max_depth = 0;
	
	public static void main(String[] args) {
		readInput();
		int [] data = new int[size];
		GenerateSortedData(data, size);
		Sort(data, size,"Sorted Data");

		GenerateNearlySortedData(data, size);
		Sort(data, size,"Nearly Sorted Data");
		
		GenerateReverselySortedData(data, size);
		Sort(data, size,"Reversely Sorted Data");
		
		GenerateRandomData(data, size);
		Sort(data, size,"Random Data");
			
		System.out.println("\nProgram Completed Successfully.");
		
	}
	
	@SuppressWarnings("resource")
	public static void readInput(){
		System.out.println("  I:\tInsertion Sort");
		System.out.println("  M:\tMergeSort");
		System.out.println("  Q:\tQuickSort");
		System.out.println("  S:\tSTLSort");
	    System.out.println("Enter sorting algorithm:");
	    Scanner reader = new Scanner(System.in);
	    sortAlg = reader.next();
	    System.out.println(sortAlg);
		String sortAlgName = "";
		
		if(sortAlg.equals("I"))
			sortAlgName = "Insertion Sort";
		else if(sortAlg.equals("M"))
			sortAlgName = "MergeSort";
		else if(sortAlg.equals("Q"))
			sortAlgName = "QuickSort";
		else if(sortAlg.equals("S"))
			sortAlgName = "STLSort";
		else {
			System.out.println("Unrecognized sorting algorithm Code:"+sortAlg);
			System.exit(1);
		}
		System.out.println("Enter input size: ");
	    size = reader.nextInt();
		System.out.println("\nSorting Algorithm: " + sortAlgName);
        System.out.println("\nInput Size = "  + size);
	}
	
	/******************************************************************************/

	public static void GenerateSortedData(int data[], int size)
	{
		int i;
		
		for(i=0; i<size; i++)
			data[i] = i * 3 + 5;
	}

	/*****************************************************************************/
	public static void GenerateNearlySortedData(int data[], int size)
	{
		int i;
		
		GenerateSortedData(data, size);
		
		for(i=0; i<size; i++)
			if(i % 10 == 0)
				if(i+1 < size)
					data[i] = data[i+1] + 7;
	}

	/*****************************************************************************/

	public static void GenerateReverselySortedData(int data[], int size)
	{
		int i;
		
		for(i = 0; i < size; i++)
			data[i] = (size-i) * 2 + 3;
	}
	
	/*****************************************************************************/

	public static void GenerateRandomData(int data[], int size)
	{
		int i;
		for(i = 0; i < size; i++)
			data[i] = new Random().nextInt(10000000);
	}

	/*****************************************************************************/

	
	public static void Sort(int[] data, int size,  String string)
	{

		System.out.print("\n"+string+":");
		if (OUTPUT_DATA)
		{
			printData(data, size, "Data before sorting:");
		}

		// Sorting is about to begin ... start the timer!
		long start_time = System.nanoTime();
			if (sortAlg.equals("I"))
			{
			InsertionSort(data, size);
			}
			else if (sortAlg.equals("M"))
			{
			MergeSort(data, 0, size-1);
			}
			else if (sortAlg.equals("Q"))
			{
			QuickSort(data, 0, size-1, max_depth);
			System.out.print("\nRecursion Depth: " + max_depth);
			}
			else if (sortAlg.equals("S"))
			{
			STLSort(data, size);
			}
		else
		{
			System.out.print("Invalid sorting algorithm!");
			System.out.print("\n");
			System.exit(1);
		}

		// Sorting has finished ... stop the timer!
		
		double elapsed = System.nanoTime()-start_time;
		elapsed=elapsed/1000000;


		if (OUTPUT_DATA)
		{
			printData(data, size, "Data after sorting:");
		}


		if (IsSorted(data, size))
		{
			System.out.print("\nCorrectly sorted ");
			System.out.print(size);
			System.out.print(" elements in ");
			System.out.print(elapsed);
			System.out.print("ms");
		}
		else
		{
			System.out.print("ERROR!: INCORRECT SORTING!");
			System.out.print("\n");
		}
		System.out.print("\n-------------------------------------------------------------\n");
	}
	
	/*****************************************************************************/

	public static boolean IsSorted(int data[], int size)
	{
		int i;
		
		for(i=0; i<(size-1); i++)
		{
			if(data[i] > data[i+1])
				return false;
		}
		return true;
	}
	
	/*****************************************************************************/

	public static void InsertionSort(int data[], int size)
	{
		for(int i = 1; i < size; i++) {
			int temp = data[i];
			int j = i - 1;
			while(j >= 0 && data[j] > temp) {
				data[j + 1] = data[j];
				j--;
			}
			data[j + 1] = temp;
		}
	}

	/*****************************************************************************/

	public static void MergeSort(int data[], int lo, int hi)
	{
		if (lo == hi) {
			return;
		}

		int mid = (int) ((lo + hi) / 2);

		MergeSort(data, lo, mid);
		MergeSort(data, mid + 1, hi);
		Merge(data, lo, mid, hi);
	}

	private static void Merge(int data[], int lo, int mid, int hi) {
		int n1 = mid - lo + 1;
		int n2 = hi - mid;

		int[] left = new int[n1 + 1];
		int[] right = new int[n2 + 1];

		for (int i = 0; i < n1; i++) {
			left[i] = data[lo + i];
		}
		for (int j = 0; j < n2; j++) {
			right[j] = data[mid + 1 + j];
		}

		int leftIndex = 0;
		int rightIndex = 0;
		int mainIndex = lo;

		while (leftIndex < n1 && rightIndex < n2) {
			if (left[leftIndex] < right[rightIndex]) {
				data[mainIndex] = left[leftIndex];
				leftIndex++;
				mainIndex++;
			} else {
				data[mainIndex] = right[rightIndex];
				rightIndex++;
				mainIndex++;
			}
		}
		while (leftIndex < n1) {
			data[mainIndex] = left[leftIndex];
			leftIndex++;
			mainIndex++;
		}
		while (rightIndex < n2) {
			data[mainIndex] = right[rightIndex];
			rightIndex++;
			mainIndex++;
		}
	}

	/*****************************************************************************/

	public static void QuickSort(int data[], int lo, int hi, int depth)
	{
		if (lo >= hi) {
			return;
		}

		if (depth > max_depth) {
			max_depth = depth;
		}

		// Insertion Sort Optimization.
		if (hi - lo + 1 <= 40) {
			int size = hi - lo + 1; // Calculate remaining size. Size is not guaranteed to be 40.
			int[] temp = new int[size]; // Create temporary array.
			System.arraycopy(data, lo, temp, 0, size); // Copy remaining elements into temp array. Asymptotically this is O(1).
			InsertionSort(temp, size); // Sort the remaining items.
			System.arraycopy(temp, 0, data, lo, size); // Asymptotically O(1) as well.
			return; // Important!! Return after sorting remaining elements, otherwise recursive call continues.
		}

		int mid = Partition(data, lo, hi);
		QuickSort(data, lo, mid - 1, depth + 1);
		QuickSort(data, mid + 1, hi, depth + 1);
	}

	public static int Partition(int data[], int lo, int hi) {
		// Randomization Pivot Optimization.
		Random rand = new Random();
		int randomIndex = rand.nextInt(hi - lo + 1) + lo;
		swap(hi, randomIndex, data);

		// // Median_Of_Three Optimization.
		// int medianIndex = Median_Of_Three(data, lo, hi);
		// swap(hi, medianIndex, data);

		// // Pure Median Optimization. // Stack Overflows at high n.
		// int medianIndex = Median(data, lo, hi);
		// swap(hi, medianIndex, data);

		int pivot = data[hi];
		int trace = lo - 1;
		for (int i = lo; i < hi; i++) {
			if (data[i] <= pivot) {
				swap(i, trace + 1, data);
				trace++;
			}
		}
		swap(trace + 1, hi, data);
		return trace + 1;
	}

	private static int Median_Of_Three(int data[], int lo, int hi) {
		Random rand = new Random();
		/* Select three random indices to retrieve random elements from data. */
		int index1 = rand.nextInt(hi - lo + 1) + lo;
		int index2 = rand.nextInt(hi - lo + 1) + lo;
		int index3 = rand.nextInt(hi - lo + 1) + lo;
		int[] temp = {data[index1], data[index2], data[index3]};

		int median = Median(temp, 0, temp.length - 1);

		if (median == data[index1]) {
			return index1;
		} else if (median == data[index2]) {
			return index2;
		} else {
			return index3;
		}
	}

	private static int Median(int data[], int lo, int hi) {
		int n = hi - lo + 1;
		int i = (int) Math.floor((n - 1) / 2);
		return Select(data, lo, hi, i);
	}

	private static int Select(int data[], int lo, int hi, int i) {
		if (lo == hi) {
			return lo;
		}

		int mid = Partition_Median(data, lo, hi);
		if (i == mid) {
			return mid;
		}
		if (i < mid) {
			return Select(data, lo, mid - 1, i);
		}

		return Select(data, mid + 1, hi, i);
	}

	private static int Partition_Median(int data[], int lo, int hi) {
		int pivot = data[hi]; // Choose last element of data as pivot.
		int trace = lo - 1;
		for (int i = lo; i < hi; i++) { // Loop from lo to hi.
			if (data[i] <= pivot) {
				swap(i, trace + 1, data); // Swap data[i] with previous data value.
				trace++;
			}
		}
		swap(trace + 1, hi, data); // Swap pivot into place.
		return trace + 1; // Return pivot index.
	}

	/*****************************************************************************/

	public static void STLSort(int data[], int size)
	{  
		Arrays.sort(data);
	}

	/*****************************************************************************/
	
	public static void swap(int x , int y ,int data[])
	{
		int temp = data[x];
		data[x] = data[y];
	    data[y] = temp;
	}
	
	/*****************************************************************************/
	
	public static void printData(int[] data, int size, String title)
	{
		int i;

		System.out.print("\n");
		System.out.print(title);
		System.out.print("\n");
		for (i = 0; i < size; i++)
		{
			System.out.print(data[i]);
			System.out.print(" ");
			if (i % 10 == 9 && size > 10)
			{
				System.out.print("\n");
			}
		}
	}

}
