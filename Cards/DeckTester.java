public class DeckTester {
	public static void main(String[] args) {
		Card[] player1Hand = new Card[7];
		Card[] player2Hand = new Card[7];

		int hand1 = 0;
		int hand2 = 0;

		Deck myDeck = new Deck();

		System.out.println("New Deck");
		System.out.println(myDeck);

		myDeck.shuffle();
		System.out.println("Shuffled Deck");
		System.out.println(myDeck);


		for(int i = 0; i < 7; i++) {
			player1Hand[i] = myDeck.deal();
			hand1++;
			player2Hand[i] = myDeck.deal();
			hand2++;
		}

		System.out.print("Player 1 Hand: ");
		for(int i = 0; i < hand1; i++) {
			if(player1Hand[i] != null) {
				System.out.print(" " + player1Hand[i]);
			}
		}

		System.out.print("\nPlayer 2 Hand:");
		for(int i = 0; i < hand2; i++) {
			if(player2Hand[i] != null) {
				System.out.print(" " + player2Hand[i]);
			}
		}

		System.out.println("\nDeck After Dealing 14");
		System.out.println(myDeck);
		myDeck.shuffle();

		System.out.println("Shuffled deck");
		System.out.println(myDeck);
		System.out.println();

		myDeck.returnToDeck(player1Hand[hand1 - 1]);
		player1Hand[hand1 - 1] = null;
		hand1--;

		System.out.println(myDeck);
		System.out.println();

		player1Hand[hand1] = myDeck.deal();
		hand1++;

		myDeck.returnToDeck(player1Hand);
		myDeck.returnToDeck(player2Hand);

		System.out.println("\nVerify full deck");
		System.out.println(myDeck);
	}
}