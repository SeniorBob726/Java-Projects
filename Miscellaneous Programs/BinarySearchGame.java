import java.util.Scanner;

public class BinarySearch {
	public static String parseResponse(String response) {
		switch(response) {
			case "e":
			case "equal":
			case "equal to":
				response = "e";
				break;
			case "gt":
			case "greater":
			case "greater than":
				response = "gt";
				break;
			case "lt":
			case "less":
			case "less than":
				response = "lt";
				break;
		}

		return response;
	}

	public static void main(String[] args) {
		int low = 1;
		int high = 100;
		int guess;

		Scanner scanner = new Scanner(System.in);
		System.out.print("Guess a number between " + low + " and " + high + ": ");
		String response = "";

		while(true) {
			guess = (low + high) / 2;
			System.out.println("Is your number greater than, less than, or equal to " + guess);
			response = parseResponse(scanner.next());

			switch(response) {
				case "gt":
					low = guess + 1;
					break;
				case "lt":
					high = guess - 1;
					break;
				case "e":
					return;
			}
		}
	}
}