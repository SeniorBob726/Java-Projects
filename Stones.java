import java.util.Scanner;

public class Stones {
	public static void main(String[] args) {
		int stonesLeft = (int)(3 * Math.random()) + 8;
		int stonesTaken = 0, aiStonesTaken = 0;
		String winner = "You Lose!";
		Scanner scanner = new Scanner(System.in);

		if(stonesLeft == 8) {
			System.out.println("You can go first this round.");
		}
		else {
			System.out.println("The computer will make the first move this round.");
			aiStonesTaken = stonesLeft - 8;
			stonesLeft -= aiStonesTaken;
			System.out.println("  The computer took " + aiStonesTaken + " stones.");
		}

		while(stonesLeft > 0) {
			System.out.println("There are " + stonesLeft + " stones left.");
			System.out.print("Do you want to take 1, 2 or 3 stones?\n  ");
			stonesTaken = scanner.nextInt();
			stonesLeft -= stonesTaken;
			if(stonesLeft == 0) {
				winner = "You Win!";
				break;
			}

			if(stonesLeft > 4) {
				aiStonesTaken = stonesLeft - 4;
			}
			else {
				aiStonesTaken = stonesLeft;
			}
			stonesLeft -= aiStonesTaken;
			System.out.println("  The computer took " + aiStonesTaken + " stones.");
		}

		System.out.println(winner);
	}
}