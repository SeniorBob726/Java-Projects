import java.util.Scanner;

public class ArrayPractice {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter a length for your array: ");
		int length = scanner.nextInt();

		int min = Integer.MAX_VALUE;
		double avg = 0;
		int max = Integer.MIN_VALUE;
		System.out.print(min + " " + max);

		if(length > 0) {
			int[] array = new int[length];
			for(int i = 0; i < length; i++) {
				System.out.print("Enter a number for index " + i + ": ");
				array[i] = scanner.nextInt();
				min = Math.min(min, array[i]);
				avg += array[i];
				max = Math.max(max, array[i]);
			}

			avg /= length;

			System.out.println("Minimum: " + min + ", " + "Average: " + avg + ", " + "Maximum: " + max);
		}

	}
}