import java.util.Scanner;

public class UICardGame {
	private static Scanner scanner = new Scanner(System.in);
	private Deck deck;
	private GameRules rules;
	private Card[] discardedPile;
	private int dPSize;
	private double pot;
	private PokerPlayer p1;
	private ComputerPokerPlayer computer;

	public UICardGame(String name, double balance) {
		deck = new Deck();
		rules = new GameRules();
		discardedPile = new Card[52];
		dPSize = 0;
		pot = 0.0;
		p1 = new PokerPlayer(name, rules.getHandSize(), balance);
		computer = new ComputerPokerPlayer("Computer", rules.getHandSize(), balance);
	}

	private void bet(PokerPlayer player) {
		System.out.println("- Your hand is: " + player.showHand() + " -");
		while(true) {
			System.out.print("Enter the amount you would like to bet: ");
			try {
				double bet = scanner.nextDouble();
				if(player.bet(bet)) {
					pot += bet;
					System.out.println("- Your new balance is $" + String.format("%.2f", player.getBalance()) + " -");
					break;
				}
				else {
					System.out.println("- Your balance is $" + String.format("%.2f", player.getBalance()) + " -");
				}
			}
			catch(Exception e) {
				System.out.println("Invalid input");
				scanner.nextLine();
			}
		}
	}

	private void discard(Player player) {
		System.out.println("- Your hand is: " + player.showHand() + " -");
		while(true) {
			System.out.print("Enter the index of the card you want to discard (-1 to not discard): ");
			int input = scanner.nextInt();
			if(input < 0 || input > player.getSize()) {
				break;
			}
			discardedPile[dPSize++] = player.discard(input);
			System.out.println("- Removed: " + discardedPile[dPSize - 1] + " -");
		}
		player.fixCards();

		for(int i = player.getSize(); i < rules.getHandSize(); i++) {
			player.setCard(deck.deal());
		}
	}

	public void play() {
		deck.shuffle();

		for(int i = 0; i < rules.getHandSize(); i++) {
			p1.setCard(deck.deal());
			computer.setCard(deck.deal());
		}

		bet(p1);
		discard(p1);
		bet(p1);

		rules.processHand(p1.getHand());
	}

	public static void main(String[] args) {
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		double balance = 0.0;
		do {
			try {
				System.out.print("Enter your balance: ");
				balance = scanner.nextDouble();
			}
			catch(Exception e) {
				System.out.println("Invalid input");
				scanner.nextLine();
			}
		} while(balance == 0.0);

		UICardGame pokerGame = new UICardGame(name, balance);
		pokerGame.play();
	}
}