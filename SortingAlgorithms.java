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

	public <T extends Comparable<T>> T[] merge(T[] a, T[] b) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length + b.length);
		int indexA = 0;
		int indexB = 0;
		for(int i = 0; i < a.length + b.length; i++) {
			if(indexA >= a.length) {
				System.arraycopy(b, indexB, array, indexA, array.length - indexA - indexB);
				return array;
			}
			if(indexB >= b.length) {
				System.arraycopy(a, indexA, array, indexB, array.length - indexA - indexB);
				return array;
			}
			if(a[indexA].compareTo(b[indexB]) < 0) {
				array[i] = a[indexA];
				indexA++;
			}
			else {
				array[i] = b[indexB];
				indexB++;
			}
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> void mergeSort(T[] array) {
		System.out.println(Arrays.toString(array));
		T[] a = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length / 2);
		T[] b = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - a.length);
		System.arraycopy(array, 0, a, 0, a.length);
		System.arraycopy(array, a.length, b, 0, b.length);

		if(a.length > 1) {
			mergeSort(a);
		}
		if(b.length > 1) {
			mergeSort(b);
		}
		array = merge(a, b);
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
		s.mergeSort(s.getArray());
		s.printArray();
	}
}