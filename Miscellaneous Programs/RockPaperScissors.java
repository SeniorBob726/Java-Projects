import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
	public static int winner(int input, int ai) {
		if(input == ai) {
			return 2;
		}
		else {
			switch(input) {
				case 0:
					if(ai == 1){
						return 0;
					}
					else {
						return 1;
					}
				case 1:
					if(ai == 2){
						return 0;
					}
					else {
						return 1;
					}
				case 2:
				default:
					if(ai == 0){
						return 0;
					}
					else {
						return 1;
					}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		System.out.print("Rock (0), Paper (1), or Scissors (2): ");
		int input = scanner.nextInt();

		int ai = random.nextInt(3);
		System.out.println(ai);

		if(winner(input, ai) == 0) {
			System.out.println("You lose!");
		}
		else if(winner(input, ai) == 1) {
			System.out.println("You win!");
		}
		else {
			System.out.println("Tie!");
		}
	}
}