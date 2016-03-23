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
		System.out.println(output.substring(0, output.length() - 3) + "]");
	}

	public static void main(String[] args) {
		SortingAlgorithms s = new SortingAlgorithms();
		s.shuffle();
		s.printArray();
	}
}