package MyCollection.Sort;

public class SelectionSort {
	
	/**
	 * Sorts the specified array of integers using the selection sort algorithm
	 * @param <T>
	 * @param data the array to be sorted
	 */
	public static <T extends Comparable<? super T>> void selectionSort(T[] data) {
		int min;
		T temp;
		for(int index = 0 ; index < data.length-1 ; index++) {
			min = index;
			for(int scan = index+1; scan <data.length ; scan++) {
				if(data[scan].compareTo(data[min]) < 0) {
					min = scan;
				}
			}
			/**Swap the values*/
			temp = data[min];
			data[min] = data[index];
			data[index] = temp;
		}
	}
}
