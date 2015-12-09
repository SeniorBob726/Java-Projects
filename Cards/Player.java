public class Player {
	private String name;
	private Card[] hand;
	private int size;
	private final int MAX_SIZE;

	public Player(String n, int max) {
		name = n;
		hand = new Card[max];
		size = 0;
		MAX_SIZE = max;
	}

	public void addCard(Card c) {
		hand[size] = c;
		size++;
	}

	public Card discard(int i) {
		Card c = hand[i];
		hand[i] = null;
		size--;

		return c;
	}

	public Card[] discard() {
		hand = new Card[MAX_SIZE];
		size = 0;

		return hand;
	}

	public String showHand() {
		String output = "";
		for(int i = 0; i < hand.length; i++) {
			output += hand[i] + ", ";
		}

		return output.substring(0, output.length() - 2);
	}

	public void fixCards() {
		int j = 0;
		for(int i = 0; i < hand.length; i++) {
			if(hand[i] != null) {
				hand[j++] = hand[i];
			}
		}
	}
}