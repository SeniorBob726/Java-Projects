public class GameRules {
	private static boolean elementExistsInArray(int[] array, int element) {
		for(int i : array) {
			if(i == element) {
				return true;
			}
		}
		return false;
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

	private int[] threeOfAKind(Card[] hand) {
		Card.sortByFaceValue(hand);
		int[] pairs = new int[(int)(hand.length / 3)];
		int size = 0;

		int check = hand[0].getFaceValue();
		for(int i = 1; i < hand.length; i++) {
			if(!elementExistsInArray(pairs, check) && check == hand[i].getFaceValue()) {
				pairs[size++] = check;
			}
			check = hand[i].getFaceValue();
		}
		return pairs;
	}

	private int[] pairs(Card[] hand) {
		Card.sortByFaceValue(hand);
		int[] pairs = new int[(int)(hand.length / 2)];
		int size = 0;

		int check = hand[0].getFaceValue();
		for(int i = 1; i < hand.length; i++) {
			if(!elementExistsInArray(pairs, check) && check == hand[i].getFaceValue()) {
				pairs[size++] = check;
			}
			check = hand[i].getFaceValue();
		}
		return pairs;
	}

	public void processHand(Card[] hand) {
		System.out.println(isFlush(hand));
		System.out.println(isStraight(hand));
		int[] pairs = pairs(hand);
		for(int i = 0; i < pairs.length; i++) {
			System.out.println(pairs[i]);
		}
	}
}