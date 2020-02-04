package MyCollection.Sort;

public class QuickSort {
	
	/**
	 * Sorts the specified array of objects using the quickSort algorithm
	 * @param <T>
	 * @param data the array to be sorted
	 * @param min the integer representation of minimum value
	 * @param max the integer representation if maximum value
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] data , int min , int max) {
		int indexofpartition;
		if(max - min > 0) {
			/**Create partitions*/
			indexofpartition = findPartition(data , min , max);
			
			/**Sort the left side*/
			quickSort(data , min , indexofpartition - 1);
			
			/**Sort the right side*/
			quickSort(data , indexofpartition + 1 , max);
		}
	}

	/**
	 * Used by the quick sort algorithm to find the partition
	 * @param <T>
	 * @param data the array to be sorted
	 * @param min the integer representation of minimum value
	 * @param max the integer representation of maximum value
	 * @return
	 */
	private static <T extends Comparable<? super T>> int findPartition (T[] data , int min , int max) {
		int left , right;
		T temp , partitionElement;
		int middle = (min + max)/2;
		
		//use middle element as partition
		partitionElement = data[middle];
		left = min;
		right = max;
		
		while(left < right) {
			/**search for element that is > the partitionElement*/
			while(data[left].compareTo(partitionElement) < 0) {
				left++;
			}
			
			/**search for element that is < the partitionElement*/
			while(data[right].compareTo(partitionElement) > 0) {
				right--;
			}
			
			/**swap the elements*/
			if(left<right) {
				temp = data[left];
				data[left] = data[right];
				data[right] = temp;
			}
		}
		
		/**Move partition element to partition index*/
		temp = data[min];
		data[min] = data[right];
		data[right] = temp;
		
		return right;		
	}
}
