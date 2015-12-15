import java.util.Scanner;

public class UICardGame {
	private static Scanner scanner = new Scanner(System.in);
	private Deck deck;
	private GameRules rules;
	private Card[] discardedPile;
	private int dPSize;
	private double kitty;
	private PokerPlayer p1;
	private ComputerPokerPlayer computer;

	public UICardGame(String name, double balance) {
		deck = new Deck();
		rules = new GameRules();
		discardedPile = new Card[52];
		dPSize = 0;
		kitty = 0.0;
		p1 = new PokerPlayer(name, rules.getHandSize(), balance);
		computer = new ComputerPokerPlayer("Computer", rules.getHandSize(), 1000);
	}

	private void bet(PokerPlayer player) {
		System.out.println("- Your hand is: " + player.showHand() + " -");
		while(true) {
			System.out.print("Enter the amount you would like to bet: ");
			try {
				double bet = scanner.nextDouble();
				if(bet >= 0) {
					if(player.bet(bet)) {
						kitty += bet;
						System.out.println("- Your new balance is $" + String.format("%.2f", player.getBalance()) + " -");
						System.out.println("- The kitty is now $" + String.format("%.2f", kitty) + " -");
						break;
					}
					else {
						System.out.println("- Your balance is $" + String.format("%.2f", player.getBalance()) + " -");
					}
				}
				else {
					System.out.println("- Invalid input -");
				}
			}
			catch(Exception e) {
				System.out.println("- Invalid input -");
				scanner.nextLine();
			}
		}
	}

	private void discard(PokerPlayer player) {
		System.out.println("- Your hand is: " + player.showHand() + " -");
		while(true) {
			System.out.print("Enter the index of the card you want to discard (-1 to not discard): ");
			try {
				int input = scanner.nextInt();
				if(input == -1) {
					break;
				}
				else if(player.getHand()[input] != null) {
					discardedPile[dPSize++] = player.discard(input);
					System.out.println("- Discarded: " + discardedPile[dPSize - 1] + " -");
				}
				else {
					System.out.println("- This card was already discarded -");
				}
			}
			catch(Exception e) {
				System.out.println("- Invalid input -");
				scanner.nextLine();
			}
		}
		player.fixCards();

		String drawOutput = "- You drew: ";
		for(int i = player.getSize(); i < rules.getHandSize(); i++) {
			Card card = deck.deal();
			player.setCard(card);
			drawOutput += card + ", ";
		}
		System.out.println(drawOutput.substring(0, drawOutput.length() - 2) + " -");
	}

	private void endGame(PokerPlayer player, ComputerPokerPlayer computer) {
		PokerHand playerHand = rules.processHand(player.getHand());
		PokerHand computerHand = rules.processHand(computer.getHand());
		System.out.println("- Your hand is: " + player.showHand() + " -");
		System.out.println(playerHand);
		System.out.println("- The computer's hand is: " + computer.showHand() + " -");
		System.out.println(computerHand);
		System.out.println();

		switch(playerHand.compareTo(computerHand)) {
			case 1:
				System.out.println("- You won! -");
				break;
			case -1:
				System.out.println("- You lost! -");
				break;
			case 0:
				System.out.println("- There was a tie! -");
				break;
		}
	}

	public void play() {
		deck.shuffle();

		for(int i = 0; i < rules.getHandSize(); i++) {
			p1.setCard(deck.deal());
			computer.setCard(deck.deal());
		}

		bet(p1);
		System.out.println();
		discard(p1);
		System.out.println();
		bet(p1);
		System.out.println();

		endGame(p1, computer);
}

	public static void main(String[] args) {
		System.out.print("5 Card Draw\n");
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		double balance = 0.0;
		do {
			try {
				System.out.print("Enter your balance: ");
				balance = scanner.nextDouble();
			}
			catch(Exception e) {
				System.out.println("- Invalid input -");
				scanner.nextLine();
			}
		} while(balance == 0.0);
		System.out.println();

		UICardGame pokerGame = new UICardGame(name, balance);
		pokerGame.play();
	}
}