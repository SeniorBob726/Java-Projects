import java.util.Arrays;
import java.util.Comparator;

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

	public static Card[] sortBySuit(Card[] array) { // Sort cards in alphabetical order by suit
		Arrays.sort(array, new Comparator<Card>() {
			@Override
			public int compare(Card a, Card b) {
				return a.getSuit().compareTo(b.getSuit());
			}
		});

		return array;
	}

	public static Card[] sortByFaceValue(Card[] array) { // Sort cards in ascending order by faceValue
		Arrays.sort(array, new Comparator<Card>() {
			@Override
			public int compare(Card a, Card b) {
				return a.getFaceValue() - b.getFaceValue();
			}
		});

		return array;
	}
}