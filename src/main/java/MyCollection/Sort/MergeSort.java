package MyCollection.Sort;

public class MergeSort {
	
	@SuppressWarnings("unchecked")
	/**
	 * Sorts the specified array of objects using the merge sort algorithm
	 * @param <T>
	 * @param data the array to be sorted
	 * @param min the integer representation of the minimum value 
	 * @param max the integer representation of the maximum value 
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] data , int min , int max) {
		T[] temp;
		int index1 , left , right;
		
		/**return on list of length one*/
		if(min == max) {
			return;
		}
		
		/**find the length and the midpoint of the list*/
		int size = 	max - min + 1;
		int pivot = (min + max)/2;
		temp = (T[])(new Comparable[size]);
		mergeSort(data , min , pivot); //sort left and half of list
		mergeSort(data , pivot+1 , max); //sort right and half of list
		
		/**copy sorted data into workspace*/
		for(index1 = 0 ; index1 < size ; index1++) {
			temp[index1] = data[min + index1];
		}
		
		/**merge the two sorted lists*/
		left = 0;
		right = pivot - min + 1;
		for(index1 = 0 ; index1 < size; index1++) {
			if(right <= max - min) {
				if(left <= pivot - min) {
					if(temp[left].compareTo(temp[right]) > 0) {
						data[index1 + min] = temp[right++];
					}
					else {
						data[index1 + min] = temp[left++];
					}
				}
				else {
					data[index1 + min] = temp[right++];
				}
			}
			else {
				data[index1 + min] = temp[left++];
			}
		}
	}
}
