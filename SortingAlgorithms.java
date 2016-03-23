import java.util.Arrays;

public class SortingAlgorithms {
	private Integer[] array = {-78, -69, -45, -43, -42, -36, -24, 10, 21, 23, 30, 31, 36, 50, 52, 55, 62, 74, 88, 92};

	public void shuffle() { // Fisher-Yates Shuffle
		int m = array.length;
		while(m > 0) {
			m--;
			int i = (int)(Math.random() * m);
			Integer t = array[m];
			array[m] = array[i];
			array[i] = t;
		}
	}

	public Integer[] getArray() {
		return array;
	}

	public void printArray() {
		String output = "[";
		for(Integer i : array) {
			output += i + ", ";
		}
		System.out.println(output.substring(0, output.length() - 2) + "]");
	}

	public <T extends Comparable<T>> void selectionSort(T[] array) {
		for(int n = array.length - 1; n > 0; n--) {
			int index = 0;
			for(int i = 1; i < n; i++) {
				if(array[i].compareTo(array[index]) > 0) {
					index = i;
				}
			}
			T swap = array[n];
			array[n] = array[index];
			array[index] = swap;
		}
	}

	public <T extends Comparable<T>> void insertionSort(T[] array) {
		for(int n = 0; n < array.length; n++) {
			for(int i = n; i > 0; i--) {
				if(array[i].compareTo(array[i - 1]) < 0) {
					T swap = array[i];
					array[i] = array[i - 1];
					array[i - 1] = swap;
				}
				else {
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		SortingAlgorithms s = new SortingAlgorithms();
		s.shuffle();
		s.printArray();
		s.selectionSort(s.getArray());
		s.printArray();

		s.shuffle();
		s.printArray();
		s.insertionSort(s.getArray());
		s.printArray();
	}
}