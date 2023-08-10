import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 
 * @author Parth Patel
 *
 */


public class Sort {
	
	/**
	 * Implement insertion sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void insertionsort(T[] arr) {
		int j = 0;
		for(int i = 1; i < (arr.length); i++){
			j = i;
			
			while( j > 0 && (arr[j].compareTo(arr[j - 1]) == -1)){
				T temp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = temp;
				j--;
			}
		}
	}
	
	
	/**
	 * Implement quick sort. 
	 * 
	 * Use the provided random object to select your pivots.
	 * For example if you need a pivot between a (inclusive)
	 * and b (exclusive) where b > a, use the following code:
	 * 
	 * int pivotIndex = r.nextInt(b - a) + a;
	 * 
	 * It should be:
	 *  inplace
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void quicksort(T[] arr, Random r) {
		int i = arr.length;
		quickhelper(arr, i);
	}
	
	private static <T extends Comparable<T>> void quickhelper(T[] arr, int i) {
		
		if( i <= 1){
			return;
		}
		Random r = null;
		int pivotIndex = r.nextInt(arr.length - 1) + 1;
		int j = 0;
		int k = i - 1;
		
		while(j < k){
			while (arr[j].compareTo(arr[pivotIndex]) == -1){
				j++;
			}
			while (arr[k].compareTo(arr[pivotIndex]) == 1){
				k--;
			}
			
			T temp = arr[j];
			arr[j] = arr[k];
			arr[k] = temp;
			
		}
		
		T[] arr2 = (T[]) new Comparable[arr.length];
		for(int l = j + 1; l < arr.length; l++ ){
			arr2[l] = arr[l]; 
		}
		
		quickhelper(arr, j);
		quickhelper(arr2, (i - j - 1));
	}
	
	/**
	 * Implement merge sort.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n log n)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 *  
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<T>> T[] mergesort(T[] arr) {
		T[] arr2 = (T[]) new Comparable[arr.length];
		for(int i = 1; i < arr.length; i *= 2){
			for(int j = 0; j < (arr.length - i); j += (2*i)){
				arr2[j] = arr[j];
				int temp2 = (2*i < arr.length - j) ? 2*i : arr.length - j;
				mergehelper(arr2, i, temp2);
			}
		}
		
		return arr;
	}
	
	private static <T extends Comparable<T>> void mergehelper(T[] arr, int temp1, int temp2){
		int j = temp1;
		int i = 0;
		int k = 0;
		T[] temparr = (T[]) new Comparable[temp2];
		
	while( i < temp1 && j < temp2){
		if(arr[i].compareTo(arr[j]) == -1){
			temparr[k] = arr[i];
			i++;
		} else {
			temparr[k] = arr[j];
			j++;
		}
		
		k++;
	}
	
	while (i < temp1){
		temparr[k] = arr[j];
		i++;
		k++;
	}
	
	while(j < temp2) {
		temparr[k] = arr[j];
		j++;
		k++;
	}
	
	for(int z = 0; z < temp2; z++){
		arr[z] = temparr[z];
	}
	
	}
	
	/**
	 * Implement radix sort
	 * 
	 * Hint: You can use Integer.toString to get a string
	 * of the digits. Don't forget to account for negative
	 * integers, they will have a '-' at the front of the
	 * string.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(kn)
	 *  
	 * And a best case running time of:
	 *  O(kn)
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] radixsort(int[] arr) {
		boolean stop = true;
		int d = 1;
		
		Queue[] fed = new Queue[10];
		
		for(int i = 0; i < 10; i++){
			fed[i] = new LinkedList<Integer>();
		}
		
		while(stop) {
		stop = false;
		for(int i = 0; i < arr.length; i++){
			int j = (arr[i] / d) % 10;
			if(j > 0){
			stop = true;
			}
			((LinkedList<Integer>) fed[j]).addLast(new Integer(arr[i]));
		}
		
		int pointer = 0;
		d *= 10;
		for(int h = 0; h < 10; h++) {
			while ( !fed[h].isEmpty()){
				Integer rtval = (Integer) ((LinkedList) fed[h]).getFirst();
				fed[h].remove(fed[0]);
				arr[pointer++] = rtval.intValue();
			}
		}
		}
		return arr;
	}
}