package MyCollection.Sort;

public class LinearSearch {
	
	/**
	 * 
	 * @param data the array to be sorted
	 * @param min the integer representation of the min value
	 * @param max the integer representation of max value
	 * @param target the element being searched for
	 * @return true if found
	 */
	public static <T extends Comparable<? super T>>  boolean linearSearch (T[] data, int min, int max, T target) {
		int index = min;
		boolean found = false;
		
		while(!found && index <= max) {
			if(data[index].compareTo(target) == 0) {
				found = true;
			}
			index++;
		}
		return found;
	}
}
