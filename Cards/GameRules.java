public class GameRules {
	private static int indexOf(int[] array, int element) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	private boolean isStraightFlush(Card[] hand, String flushSuit) {
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

		return isStraight(flushCheck);
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

	private boolean isStraight(Card[] hand) {
		boolean[] values = new boolean[14];
		for(int i = 0; i < values.length; i++) {
			values[i] = false;
		}

		for(int i = 0; i < hand.length; i++) {
			values[hand[i].getFaceValue() - 1] = true;
			if(hand[i].getFaceValue() == 14) { // Ace can be high or low
				values[0] = true;
			}
		}

		int count = 1;
		for(int i = 1; i < values.length; i++) {
			if(values[i]) {
				count++;
			}
			else {
				count = 1;
			}

			if(count == 5) {
				return true;
			}
		}
		return false;
	}

	private void repeatedCards(Card[] hand, int[] fourOfAKind, int[] threeOfAKind, int[] twoOfAKind) {
		int fourSize = 0;
		int threeSize = 0;
		int twoSize = 0;

		int check = hand[0].getFaceValue();
		for(int i = 1; i < hand.length; i++) {
			if(check == hand[i].getFaceValue()) {
				int fourIndex = indexOf(fourOfAKind, check);
				int threeIndex = indexOf(threeOfAKind, check);
				int twoIndex = indexOf(twoOfAKind, check);

				if(fourIndex != -1) {
					fourOfAKind[fourIndex] = 0;
					fourSize--;
				}
				if(threeIndex != -1) {
					fourOfAKind[fourSize++] = check;
					threeOfAKind[threeIndex] = 0;
					threeSize--;
				}
				else if(twoIndex != -1) {
					threeOfAKind[threeSize++] = check;
					twoOfAKind[twoIndex] = 0;
					twoSize--;
				}
				else {
					twoOfAKind[twoSize++] = check;
				}
			}
			check = hand[i].getFaceValue();
		}
	}

	private Card highCard(Card[] hand) {
		return hand[hand.length - 1];
	}

	public void processHand(Card[] hand) {
		Card.sortBySuit(hand);
		String flushSuit = isFlush(hand);
		System.out.println("Flush: " + flushSuit);

		Card.sortByFaceValue(hand);
		System.out.println("Straight: " + isStraight(hand));

		if(flushSuit != null) {
			System.out.println("Straight Flush: " + isStraightFlush(hand, flushSuit));
		}

		int[] fourOfAKind = new int[(int)(hand.length / 4)];
		int[] threeOfAKind = new int[(int)(hand.length / 3)];
		int[] twoOfAKind = new int[(int)(hand.length / 2)];

		repeatedCards(hand, fourOfAKind, threeOfAKind, twoOfAKind);

		String output = "fourOfAKind: ";
		for(int i = 0; i < fourOfAKind.length; i++) {
			if(fourOfAKind[i] != 0) {
				output += fourOfAKind[i] + ", ";
			}
		}
		System.out.println(output.substring(0, output.length() - 2));

		output = "threeOfAKind: ";
		for(int i = 0; i < threeOfAKind.length; i++) {
			if(threeOfAKind[i] != 0) {
				output += threeOfAKind[i] + ", ";
			}
		}
		System.out.println(output.substring(0, output.length() - 2));

		output = "twoOfAKind: ";
		for(int i = 0; i < twoOfAKind.length; i++) {
			if(twoOfAKind[i] != 0) {
				output += twoOfAKind[i] + ", ";
			}
		}
		System.out.println(output.substring(0, output.length() - 2));

		System.out.println("High Card: " + highCard(hand));
	}
}