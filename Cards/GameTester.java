// Instantiate a Deck object and two Player object's.
// Deal 5 Cards to each Player
// Print the Cards belonging to the Players, use the showHand() method.
// Allow Player to choose to discard up to 5 Cards
// Create a "discarded Cards pile".
// Fix Player's hand so that all null references are at end of array
// deal Player new Cards so they have 5.
// Show new hands. //Verify correct cards are replaced
// Show deck //verify these Cards are not in deck.
// Return both hands and discarded Cards back to deck.
// Show deck //verify full Deck

import java.util.Scanner;

public class GameTester {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Deck deck = new Deck();
		GameRules rules = new GameRules();

		Player p1 = new Player("p1", 7);
		Player p2 = new Player("p2", 7);

		deck.shuffle();

		for(int i = 0; i < 7; i++) {
			p1.setCard(deck.deal());
			p2.setCard(deck.deal());
		}

		System.out.println(p1.getName() + ": " + p1.showHand());
		rules.processHand(p1.getHand());

		System.out.println();

		System.out.println(p2.getName() + ": " + p2.showHand());
		rules.processHand(p2.getHand());

		/* Card[] discardedPile = new Card[10];
		int size = 0;

		System.out.println(p1.showHand());
		while(true) {
			System.out.print("Discard cards at index (-1 to not discard): ");
			int input = scanner.nextInt();
			if(input == -1) {
				break;
			}
			discardedPile[size++] = p1.discard(input);
		}
		p1.fixCards();
		System.out.println(p1.showHand());

		System.out.println(p2.showHand());
		while(true) {
			System.out.print("Discard cards at index (-1 to not discard): ");
			int input = scanner.nextInt();
			if(input == -1) {
				break;
			}
			discardedPile[size++] = p2.discard(input);
		}
		p2.fixCards();
		System.out.println(p2.showHand());

		String output = "";
		for(int i = 0; i < discardedPile.length; i++) {
			output += discardedPile[i] + ", ";
		}
		System.out.println(output.substring(0, output.length() - 2));*/
	}
}