public class GameRules {
	private static int indexOf(int[] array, int element) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	private boolean isFlush(Card[] hand) {
		String check = hand[0].getSuit();
		for(int i = 1; i < hand.length; i++) {
			if(!check.equals(hand[i].getSuit())) {
				return false;
			}
		}
		return true;
	}

	private boolean isStraight(Card[] hand) {
		Card.sortByFaceValue(hand);

		int check = hand[0].getFaceValue();
		for(int i = 1; i < hand.length; i++) {
			if(check + i != hand[i].getFaceValue()) {
				return false;
			}
		}
		return true;
	}

	private void repeatedCards(Card[] hand, int[] fourOfAKind, int[] threeOfAKind, int[] twoOfAKind) {
		Card.sortByFaceValue(hand);
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

	private void highCard(Card[] hand) {
		Card.sortByFaceValue(hand);

		return hand[hand.length - 1];
	}

	public void processHand(Card[] hand) {
		System.out.println("Flush: " + isFlush(hand));
		System.out.println("Straight: " + isStraight(hand));

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