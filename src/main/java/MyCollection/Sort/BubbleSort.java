package MyCollection.Sort;

public class BubbleSort {
	
	/**
	 * Sorts the specified array of objects using a bubble sort algorithm
	 * @param <T>
	 * @param data the array to be sorted
	 */
	public static <T extends Comparable<? super T>> void bubbleSort (T[] data) {
		int position , scan;
		T temp;
		for(position = data.length-1; position >= 0 ; position--) {
			for(scan = 0 ; scan<= position-1 ; scan++) {
				if(data[scan].compareTo(data[scan+1]) > 0) {
					/**Swap the values*/
					temp = data[scan];
					data[scan] = data[scan+1];
					data[scan+1] = temp;
				}
			}
		}
	}
}
