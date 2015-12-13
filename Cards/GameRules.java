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

	public void processHand(Card[] hand) {
		System.out.println(isFlush(hand));
		System.out.println(isStraight(hand));

		int[] fourOfAKind = new int[(int)(hand.length / 4)];
		int[] threeOfAKind = new int[(int)(hand.length / 3)];
		int[] twoOfAKind = new int[(int)(hand.length / 2)];

		repeatedCards(hand, fourOfAKind, threeOfAKind, twoOfAKind);

		System.out.println("fourOfAKind");
		for(int i = 0; i < fourOfAKind.length; i++) {
			System.out.println(fourOfAKind[i]);
		}
		System.out.println("threeOfAKind");
		for(int i = 0; i < threeOfAKind.length; i++) {
			System.out.println(threeOfAKind[i]);
		}
		System.out.println("twoOfAKind");
		for(int i = 0; i < twoOfAKind.length; i++) {
			System.out.println(twoOfAKind[i]);
		}
	}
}