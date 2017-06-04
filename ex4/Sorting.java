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
		double pivot = arr[end];
		int leftWall = start;
		for (int i = start; i < end; i++ ){
			// if this item is smaller than the pilot
			if (arr[i] < pivot){
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
		int start = 0;
		int end = arr.length - 1;
		mergeSortRecursive(arr, start, end);
	}

	public static void mergeSortRecursive(double[] arr, int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;
			mergeSortRecursive(arr, start, middle);
			mergeSortRecursive(arr, middle + 1, end);
			merge(arr, start, middle, end);
		}
	}
	public static void merge(double[] arr, int start, int middle, int end){
		int n1 = middle - start + 1;
		int n2 = end - middle;
		double[] L = new double[n1 + 1]; double[] R = new double[n2 + 1];
		// creating subarrays of the left and right sides of the given subarray
		for (int i = 0; i < n1; i++){
			L[i] = arr[start + i];
		}
		for (int i = 0; i < n2; i++){
			R[i] = arr[middle + i + 1];
		}
		// putting fake infinity values at the end of the arrays R, L
		L[n1] = 100000.0;
		R[n2] = 100000.0;
		int i = 0; int j = 0;
		// looping through the array, and placing the smaller element from both lists
		for (int k = start; k < end + 1; k++) {
			if (L[i] < R[j]) {
				arr[k] = L[i];
				i = i + 1;
			} else{
				arr[k] = R[j];
				j = j + 1;
			}
		}
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
		if (i < 0 || i > arr.length - 1){
			throw new ArrayIndexOutOfBoundsException();
		}
		int start = 0;
		int end = arr.length - 1;
		return selectRecursive(arr, start, end, i);
	}

	public static double selectRecursive (double[] arr, int start, int end, int i){
		// if arr is of length 1- return the only element
		if (start == end){
			return arr[start];
		}

		//create the median of medians array
		double[] medianArr = new double[(end - start)/5 + 1];
		for (int k = 0; k < medianArr.length; k++ ){
			int j = k*5 + start;
			medianArr[k] = median(arr, end, j);
		}

		// select a pivot out of the median of medians array
		double pivot = selectRecursive(medianArr, 0, medianArr.length - 1, medianArr.length/2);
		//use partition to get the pivot's location
		int middle = partition(arr, start, end, pivot);
		// continue select on an appropriate sub array
		if (middle == i){
			return arr[i];
		} else if (middle < i){
			return selectRecursive(arr, middle + 1, end, i);
		} else if (middle > i) {
			return selectRecursive(arr, start, middle - 1, i);
		}
		return -1.0;
	}

	// find the median from a set of 5 numbers, from arr. uses quicksort.
	public static double median (double[] arr, int end, int index) {
		int length = ((end + 1 -index) < 5) ? (end + 1 -index) : 5;
		double[] L = new double[length];
		for (int i = 0; i < L.length; i++){
			L[i] = arr[i + index];
		}
		quickSort(L);
		return L[length / 2];
	}

	public static int partition(double[] arr, int start, int end, double pivot){
		int leftWall = start;
		int rightWall = end;

		while (true) {
			while (arr[leftWall] < pivot){
				// move forward until finding an element bigger that the pivot
				leftWall = leftWall + 1;
			}

			while (arr[rightWall] > pivot){
				// move backwards until finding an element smaller that the pivot
				rightWall = rightWall - 1;
			}

			// break if finished switching the elements to the correct places
			if (leftWall >= rightWall){
				return rightWall;
			}
			// swap those elements
			double temp = arr[rightWall];
			arr[rightWall] = arr[leftWall];
			arr[leftWall] = temp;

		}
	}

	/**
	 * Sorts a given array using the selection sort algorithm.
	 * 
	 * Should run in complexity O(n^2) in the worst case.
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void selectionSort(double[] arr){
		for (int i = 0; i < arr.length; i++ ){
			int min = i;
			for (int j = min + 1; j < arr.length; j++){
				// if item in j is smaller than the minimum, it's the new minimum
				if (arr[j] < arr[min]){
					min = j;
				}
			}
			// swap the minimum and the element at i
			double temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
	}
	
	public static void main(String[] args) {
		double[] arr = {80.0, 2.4, 3.5, 100.3, 45.3, 4.5, 5.6, 70.4, 0.5, 30.5, 80.9, 3.0};
		//double[] arr = {5.0, 2.0, 4.0, 1.0, 3.0};
		//double[] arr = {5.0, 2.0, 8.0, 7.0, 4.0, 1.0, 3.0, 6.0};

		selectionVsQuick();
		mergeVsQuick();
		mergeVsQuickOnSortedArray();
		selectVsMerge();
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
