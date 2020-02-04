package MyCollection.Sort;

public class BinarySearch {
	
	/**
	 * 
	 * @param <T>
	 * @param data the array to be sorted
	 * @param min the integer representation of minimum value
	 * @param max the integer representation of maximum value
	 * @param target the element being searched for
	 * @return true if found
	 */
	public static <T extends Comparable<? super T>> boolean binarySearch(T[] data , int min , int max , T target) {
		boolean found = false;
		int midpoint = (min + max) / 2; //Determinar o elemento que se encontra no meio da lista
		
		if(data[midpoint].compareTo(target) == 0) {
			found = true;
		}
		else if(data[midpoint].compareTo(target) > 0) {
			if(min <= midpoint - 1) {
				found = binarySearch(data , min , midpoint - 1 , target);
			}
		}
		else if(midpoint + 1 <= max) {
			found = binarySearch(data , midpoint + 1 , max , target);
		}
		
		return found;
	}
}
