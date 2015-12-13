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

	public static void sortBySuit(Card[] array) { // Sort cards in alphabetical order by suit
		for(int i = 1; i < array.length; i++) {
			for(int j = i; j > 0; j--) {
				if(array[j].getSuit().compareTo(array[j - 1].getSuit()) < 0) {
					Card temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}
		}
	}

	public static void sortByFaceValue(Card[] array) { // Sort cards in ascending order by faceValue
		for(int i = 1; i < array.length; i++) {
			for(int j = i; j > 0; j--) {
				if(array[j].getFaceValue() < array[j - 1].getFaceValue()) {
					Card temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}
		}
	}
}