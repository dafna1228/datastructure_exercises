import java.util.Random;
import Plotter.Plotter;

public class Sorting {

	final static int SELECTION_VS_QUICK_LENGTH = 12;
	final static int MERGE_VS_QUICK_LENGTH = 15;
	final static int MERGE_VS_QUICK_SORTED_LENGTH = 12;
	final static int SELECT_VS_MERGE_LENGTH = 16;
	final static double T = 600.0;
	/**
	 * Sorts a given array using the quick sort algorithm.
	 * At each stage the pivot is chosen to be the rightmost element of the subarray.
	 * 
	 * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void quickSort(double[] arr){
		int start = 0;
		int end = arr.length - 1;
		// calling a recursive quicksort function
		quickSortRecursive(arr, start, end);
	}

	public static void quickSortRecursive(double[] arr, int start, int end){
		if (start < end) {
			int middle = partition(arr, start, end);
			quickSortRecursive(arr, start, middle - 1);
			quickSortRecursive(arr, middle + 1, end);
		}
	}

	public static int partition(double[] arr, int start, int end){
		double pilot = arr[end];
		int leftWall = start;
		for (int i = start; i < end; i++ ){
			// if this item is smaller than the pilot
			if (arr[i] < pilot){
				// move the wall one index right
				leftWall = leftWall + 1;
				// switch arr[leftWall] with arr[i]
				double temp = arr[leftWall - 1];
				arr[leftWall - 1] = arr[i];
				arr[i] = temp;
			}
		}
		// switch arr[leftWall + 1] with the pilot
		double temp = arr[leftWall];
		arr[leftWall] = arr[end];
		arr[end] = temp;

		return leftWall;
	}

	/**
	 * Sorts a given array using the merge sort algorithm.
	 * 
	 * Should run in complexity O(nlog(n)) in the worst case.
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void mergeSort(double[] arr){
		//your code comes here
	}
	

	/**
	 * finds the i'th order statistic of a given array.
	 * 
	 * Should run in complexity O(n) in the worst case.
	 * 
	 * @param arr - the array.
	 * @param i - a number between 0 and arr.length - 1.
	 * @return the number which would be at index i, if the array was to be sorted
	 */
	public static double select (double[] arr, int i){
		//your code comes here
		return 0.0;
	}
	
	/**
	 * Sorts a given array using the selection sort algorithm.
	 * 
	 * Should run in complexity O(n^2) in the worst case.
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void selectionSort(double[] arr){
		//your code comes here
	}
	
	public static void main(String[] args) {
		double[] arr = {80.0, 2.4, 3.5, 100.3, 45.3, 4.5, 5.6, 70.4, 0.5, 30.5};
		//double[] arr = {5.0, 2.0, 4.0, 1.0, 3.0};
		quickSort(arr);
		for (int i = 0; i< arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		selectionVsQuick();
		//mergeVsQuick();
		//mergeVsQuickOnSortedArray();
		//selectVsMerge();
	}
	
	/**
	 * Compares the selection sort algorithm against quick sort on random arrays
	 */
	public static void selectionVsQuick(){
		double[] quickTimes = new double[SELECTION_VS_QUICK_LENGTH];
		double[] selectionTimes = new double[SELECTION_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < SELECTION_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumSelection = 0;
			for(int k = 0; k < T; k++){
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSort(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				selectionSort(b);
				endTime = System.currentTimeMillis();
				sumSelection += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			selectionTimes[i] = sumSelection/T;
		}
		Plotter.plot("quick sort", quickTimes, "selection sort", selectionTimes);
	}
	
	/**
	 * Compares the merge sort algorithm against quick sort on random arrays
	 */
	public static void mergeVsQuick(){
		double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
		double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumMerge = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSort(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				mergeSort(b);
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			mergeTimes[i] = sumMerge/T;
		}
		Plotter.plot("quick sort", quickTimes, "merge sort", mergeTimes);
	}

	/**
	 * Compares the merge sort algorithm against quick sort on pre-sorted arrays
	 */
	public static void mergeVsQuickOnSortedArray(){
		double[] quickTimes = new double[MERGE_VS_QUICK_SORTED_LENGTH];
		double[] mergeTimes = new double[MERGE_VS_QUICK_SORTED_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < MERGE_VS_QUICK_SORTED_LENGTH; i++) {
			long sumQuick = 0;
			long sumMerge = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = j;
					b[j] = j;
				}
				startTime = System.currentTimeMillis();
				quickSort(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				mergeSort(b);
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			mergeTimes[i] = sumMerge/T;
		}
		Plotter.plot("quick sort on sorted array", quickTimes, "merge sort on sorted array", mergeTimes);
	}

	/**
	 * Compares the select algorithm against sorting an array.
	 */
	public static void selectVsMerge(){
		double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
		double[] selectTimes = new double[MERGE_VS_QUICK_LENGTH];
		long startTime, endTime;
		double x;
		Random r = new Random();
		for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
			long sumMerge = 0;
			long sumSelect = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				int index = (int)(Math.random() * size);
				startTime = System.currentTimeMillis();
				mergeSort(a);
				x = a[index];
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
				startTime = System.currentTimeMillis();
				x = select(b, index);
				endTime = System.currentTimeMillis();
				sumSelect += endTime - startTime;
			}
			mergeTimes[i] = sumMerge/T;
			selectTimes[i] = sumSelect/T;
		}
		Plotter.plot("merge sort and select", mergeTimes, "select", selectTimes);
	}
}
