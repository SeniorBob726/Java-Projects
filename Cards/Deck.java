public class Deck {
	private Card[] deck;
	public int size;

	public Deck() {
		size = 52;
		deck = new Card[size];

		int[] faceValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
		String[] names = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
		String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};

		for(int s = 0; s < suits.length; s++) {
			for(int n = 0; n < names.length; n++) {
				deck[s * 13 + n] = new Card(faceValues[n], names[n], suits[s]);
			}
		}
	}

	public Deck(int[] fv) {
		size = 52;
		deck = new Card[size];

		int[] faceValues = fv.clone();
		String[] names = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
		String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};

		for(int s = 0; s < suits.length; s++) {
			for(int n = 0; n < names.length; n++) {
				deck[s * 13 + n] = new Card(faceValues[n], names[n], suits[s]);
			}
		}
	}

	public int getSize() {
		return size;
	}

	public void shuffle() { // Fisher-Yates Shuffle
		int m = size;
		while(m > 0) {
			m--;
			int i = (int)(Math.random() * m);
			Card t = deck[m];
			deck[m] = deck[i];
			deck[i] = t;
		}
	}

	public Card deal() {
		Card c = deck[size - 1];
		deck[size - 1] = null;
		size--;

		return c;
	}

	public boolean returnToDeck(Card c) {
		if(size <= deck.length) {
			deck[size] = c;
			size++;
			return true;
		}
		return false;
	}

	public boolean returnToDeck(Card[] pile) {
		if(size + pile.length <= deck.length) {
			for(int i = 0; i < pile.length; i++) {
				deck[size + i] = pile[i];
			}
			size += pile.length;
			return true;
		}
		return false;
	}

	public String toString() {
		String output = "";
		for(int i = 0; i < size; i++) {
			if(deck[i] != null) {
				output += deck[i].toString() + "\n";
			}
		}
		return output;
	}

	public static void main(String[] args) {
		Deck deck = new Deck();
		System.out.println(deck.getSize() + " " + deck);
		deck.shuffle();
		System.out.println(deck.getSize() + " " + deck);
	}
}