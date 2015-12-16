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
		computer = new ComputerPokerPlayer("Computer", rules.getHandSize(), 100);
	}

	private double bet(PokerPlayer player) {
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
						return bet;
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

		if(player.getSize() < rules.getHandSize()) {
			String drawOutput = "- You drew: ";
			for(int i = player.getSize(); i < rules.getHandSize(); i++) {
				Card card = deck.deal();
				player.setCard(card);
				drawOutput += card + ", ";
			}
			System.out.println();
			System.out.println(drawOutput.substring(0, drawOutput.length() - 2) + " -");
		}
	}

	private void endGame(PokerPlayer player, ComputerPokerPlayer computer) {
		PokerHand playerHand = rules.processHand(player.getHand());
		PokerHand computerHand = rules.processHand(computer.getHand());
		System.out.println("- Your hand is: " + player.showHand() + " -");
		System.out.println("- The computer's hand is: " + computer.showHand() + " -");
		System.out.println("- Your best hand is " + playerHand + " -");
		System.out.println("- The computer's best hand is " + computerHand + " -");
		System.out.println();

		switch(playerHand.compareTo(computerHand)) {
			case 1:
				System.out.println("- You won $" + String.format("%.2f", kitty) + "! -");
				player.addWinnings(kitty);
				System.out.println("- Your new balance is $" + String.format("%.2f", player.getBalance()) + " -");
				break;
			case -1:
				System.out.println("- You lost! -");
				computer.addWinnings(kitty);
				break;
			case 0:
				System.out.println("- There was a tie! -");
				player.addWinnings(kitty / 2.0);
				computer.addWinnings(kitty / 2.0);
				System.out.println("- Your new balance is $" + String.format("%.2f", player.getBalance()) + " -");
				break;
		}
		for(int i = 0; i < discardedPile.length; i++) {
			if(discardedPile[i] != null) {
				deck.returnToDeck(discardedPile[i]);
			}
		}
		dPSize = 0;
		deck.returnToDeck(player.discard());
		deck.returnToDeck(computer.discard());
		kitty = 0;

		System.out.println();
		System.out.println("5 Card Draw");
		System.out.println("- Play");
		System.out.println("- Quit");
	}

	public void play() {
		deck.shuffle();

		for(int i = 0; i < rules.getHandSize(); i++) {
			p1.setCard(deck.deal());
			computer.setCard(deck.deal());
		}

		double p1Bet = bet(p1);
		System.out.println();

		double computerBet = computer.decideBet(p1Bet);
		computer.bet(computerBet);
		kitty += computerBet;
		System.out.println("- The computer bet $" + String.format("%.2f", computerBet) + " -");
		System.out.println("- The kitty is now $" + String.format("%.2f", kitty) + " -");
		System.out.println();

		discard(p1);
		System.out.println();

		int[] computerDiscard = computer.decideDiscard();
		for(int i = 0; i < computerDiscard.length; i++) {
			discardedPile[dPSize++] = computer.discard(computerDiscard[i]);
		}
		computer.fixCards();
		for(int i = 0; i < computerDiscard.length; i++) {
			computer.setCard(deck.deal());
		}
		System.out.println("- The computer discarded " + computerDiscard.length + " cards -");
		System.out.println();

		p1Bet = bet(p1);
		System.out.println();

		computerBet = computer.decideBet(p1Bet);
		computer.bet(computerBet);
		kitty += computerBet;
		System.out.println("- The computer bet $" + String.format("%.2f", computerBet) + " -");
		System.out.println("- The kitty is now $" + String.format("%.2f", kitty) + " -");
		System.out.println();

		endGame(p1, computer);
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
				System.out.println("- Invalid input -");
				scanner.nextLine();
			}
		} while(balance == 0.0);
		System.out.println();
		System.out.println("5 Card Draw");
		System.out.println("- Play");
		System.out.println("- Quit");

		UICardGame pokerGame = new UICardGame(name, balance);

		while(true) {
			System.out.print("> ");
			String input = scanner.next().toLowerCase();

			if(input.equals("quit")) {
				System.out.println("Goodbye!");
				break;
			}

			if(input.equals("play")) {
				System.out.println();
				pokerGame.play();
			}
		}
	}
}