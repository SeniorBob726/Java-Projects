public class GameRules {
	private final int HAND_SIZE = 5;

	public int getHandSize() {
		return HAND_SIZE;
	}

	private static int indexOf(int[] array, int element) { // Return index of element in array, -1 if not found
		for(int i = 0; i < array.length; i++) {
			if(array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	private void sortDescending(int[] array) { // Sort integer array in descending order
		for(int i = 1; i < array.length; i++) {
			for(int j = i; j > 0; j--) {
				if(array[j] > array[j - 1]) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}
		}
	}

	private int isStraightFlush(Card[] hand, String flushSuit) { // 2 = Royal Flush, 1 = Straight Flush, -1 = no Straight Flush
		Card[] flushSuitCards = new Card[hand.length];
		int size = 0;

		for(int i = 0; i < hand.length; i++) {
			if(flushSuit.equals(hand[i].getSuit())) {
				flushSuitCards[size++] = hand[i];
			}
		}

		Card[] flushCheck = new Card[size];
		for(int i = 0; i < size; i++) {
			flushCheck[i] = flushSuitCards[i];
		}

		if(isStraight(flushCheck) != null) {
			if(highCard(flushCheck).getFaceValue() == 14) {
				return 2;
			}
			return 1;
		}

		return -1;
	}

	private String isFlush(Card[] hand) {
		int count = 1;
		String check = hand[0].getSuit();
		for(int i = 1; i < hand.length; i++) {
			if(!check.equals(hand[i].getSuit())) {
				check = hand[i].getSuit();
				count = 1;
			}
			else {
				count++;
			}

			if(count == 5) {
				return check;
			}
		}
		return null;
	}

	private Card isStraight(Card[] hand) { // Return highest card of Straight
		boolean[] values = new boolean[15];
		for(int i = 0; i < values.length; i++) {
			values[i] = false;
		}

		for(int i = 0; i < hand.length; i++) {
			values[hand[i].getFaceValue()] = true;
			if(hand[i].getFaceValue() == 14) { // Ace can be high or low
				values[1] = true;
			}
		}

		int count = 0;
		for(int i = 1; i < values.length; i++) {
			if(values[i]) {
				count++;
			}
			else {
				count = 0;
			}

			if(count == 5) {
				for(int j = 0; j < hand.length; j++) {
					if(hand[j].getFaceValue() == i) {
						return hand[j];
					}
				}
			}
		}
		return null;
	}

	private boolean repeatedCards(Card[] hand, int[] fourOfAKind, int[] threeOfAKind, int[] twoOfAKind) {
		int fourSize = 0;
		int threeSize = 0;
		int twoSize = 0;

		int check = hand[0].getFaceValue();
		for(int i = 1; i < hand.length; i++) {
			if(check == hand[i].getFaceValue()) {
				int threeIndex = indexOf(threeOfAKind, check);
				int twoIndex = indexOf(twoOfAKind, check);

				if(threeIndex != -1) { // If number exists in threeOfAKind, shift it up to fourOfAKind
					fourOfAKind[fourSize++] = check;
					threeOfAKind[threeIndex] = 0;
					threeSize--;
				}
				else if(twoIndex != -1) { // If number exists in twoOfAKind, shift it up to threeOfAKind
					threeOfAKind[threeSize++] = check;
					twoOfAKind[twoIndex] = 0;
					twoSize--;
				}
				else { // Add duplicate to twoOfAKind
					twoOfAKind[twoSize++] = check;
				}
			}
			check = hand[i].getFaceValue();
		}

		sortDescending(fourOfAKind);
		sortDescending(threeOfAKind);
		sortDescending(twoOfAKind);

		if(twoSize > 0 && threeSize > 0) {
			return true;
		}
		return false;
	}

	private Card highCard(Card[] hand) {
		return hand[hand.length - 1];
	}

	public PokerHand processHand(Card[] hand) {
		Card.sortBySuit(hand);
		String flushSuit = isFlush(hand);

		Card.sortByFaceValue(hand);

		boolean royalFlush = false;
		boolean straightFlush = false;
		if(flushSuit != null) {
			if(isStraightFlush(hand, flushSuit) == 2) {
				royalFlush = true;
				straightFlush = true;
			}
			else if(isStraightFlush(hand, flushSuit) == 1) {
				straightFlush = true;
			}
		}

		int[] fourOfAKind = new int[(int)(hand.length / 4)];
		int[] threeOfAKind = new int[(int)(hand.length / 3)];
		int[] twoOfAKind = new int[(int)(hand.length / 2)];
		boolean fullHouse = repeatedCards(hand, fourOfAKind, threeOfAKind, twoOfAKind);

		PokerHand pokerHand = new PokerHand(royalFlush, straightFlush, fourOfAKind, fullHouse, isFlush(hand), isStraight(hand), threeOfAKind, twoOfAKind, highCard(hand));
		return pokerHand;
	}
}