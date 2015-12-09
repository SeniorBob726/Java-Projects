public class Deck {
	private Card[] deck;
	public int size;
	private static final int[] defaultFaceValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	private static final String[] defaultNames = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	private static final String[] defaultSuits = {"Spades", "Hearts", "Clubs", "Diamonds"};

	public Deck(int sz, int[] faceValues, String[] names, String[] suits) {
		size = sz;
		deck = new Card[size];

		for(int s = 0; s < suits.length; s++) {
			for(int n = 0; n < names.length; n++) {
				deck[s * names.length + n] = new Card(faceValues[n], names[n], suits[s]);
			}
		}
	}

	public Deck(int[] faceValues) {
		this(52, faceValues, defaultNames, defaultSuits);
	}

	public Deck() {
		this(defaultFaceValues);
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

	public boolean returnToDeck(Card... pile) {
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