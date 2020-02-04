package MyCollection.Sort;

public class InsertionSort {
	
	/**
	 * Sorts the specified array of objects using an insertion sort algorithm
	 * @param <T>
	 * @param data the array to be sorted
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] data) {
		for(int index = 1; index < data.length ; index++) {
			T key = data[index];
			int position = index;
			
			/**Shift larger values to the right*/
			while(position > 0 && data[position-1].compareTo(key) > 0) {
				data[position] = data[position-1];
				position--;
			}
			data[position] = key;
		}
	}
}
