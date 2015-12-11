public class GameRules {
	private boolean isFlush() {
		String check = hand[0].getSuit();
		for(int i = 1; i < hand.length; i++) {
			if(!check.equals(hand[i].getSuit())) {
				return false;
			}
		}
		return true;
	}

	public int processHand(Cards[] cards) {

	}
}