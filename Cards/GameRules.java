public class GameRules {
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
			if(check != hand[i].getFaceValue() + i)) {
				return false;
			}
		}
		return true;
	}

	private int pairs(Card[] hand) {
		Card.sortByFaceValue(hand);
		int pairs = 0;

		int check = hand[0].getFaceValue();
		for(int i = 1; i < hand.length; i++) {
			if(check == hand[i].getFaceValue())) {
				pairs++;
				if(i + 1 < hand.length) {
					check = hand[i + 1].getFaceValue();
				}
			}
		}
		return pairs;
	}

	public int processHand(Cards[] cards) {

	}
}