public class Card {
	private final int faceValue;
	private final String name;
	private final String suit;

	public Card(int v, String n, String s) {
		faceValue = v;
		name = n;
		suit = s;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public String getName() {
		return name;
	}

	public String getSuit() {
		return suit;
	}

	public String toString() {
		return name + " of " + suit;
	}
}