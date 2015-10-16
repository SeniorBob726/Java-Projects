import java.util.Scanner;

public class LunchTime {
	public static int minutesToLunch(int hours, int minutes) {
		return 60 - minutes + (12 - hours) * 60;
	}

	public static void main(String[] args) {
		Scanner scanner	= new Scanner(System.in);
		System.out.print("Enter current time (hh:mm): ");
		String currentTime = scanner.next();
		int hours = Integer.parseInt(currentTime.substring(0, 2));
		int minutes = Integer.parseInt(currentTime.substring(3, 5));
		System.out.println("There are " + minutesToLunch(hours, minutes) + " minutes until lunch.");
	}
}