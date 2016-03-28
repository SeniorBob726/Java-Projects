import java.lang.reflect.Array;
import java.util.Arrays;

public class SortingAlgorithms {
	private Integer[] sampleArray = {-78, -69, -45, -43, -42, -36, -24, 10, 21, 23, 30, 31, 36, 50, 52, 55, 62, 74, 88, 92};

	public <T> void shuffle(T[] array) { // Fisher-Yates Shuffle
		int m = array.length;
		while(m > 0) {
			m--;
			int i = (int) (Math.random() * m);
			T t = array[m];
			array[m] = array[i];
			array[i] = t;
		}
	}

	public Integer[] getArray() {
		return this.sampleArray;
	}

	public void printArray() {
		String output = "[";
		for(Integer i : this.sampleArray) {
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

	public <T extends Comparable<T>> void mergeSort(T[] array, int low, int high) {
		if(low >= high) {
			return;
		}
		int middle = (high + low) / 2;
		mergeSort(array, low, middle);
		mergeSort(array, middle + 1, high);
		for(int i = low; i <= high; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		if(array[middle].compareTo(array[middle + 1]) > 0) {
			if(high - low == 2) {

			}
		}

	}

	public static void main(String[] args) {
		SortingAlgorithms s = new SortingAlgorithms();
		s.shuffle(s.getArray());
		s.printArray();
		s.selectionSort(s.getArray());
		s.printArray();

		s.shuffle(s.getArray());
		s.printArray();
		s.insertionSort(s.getArray());
		s.printArray();

		s.shuffle(s.getArray());
		s.printArray();
		s.mergeSort(s.getArray(), 0, s.getArray().length - 1);
		s.printArray();
	}
}