public class ComputerPokerPlayer extends PokerPlayer {
	private GameRules rules;

	public ComputerPokerPlayer(String name, int max, double b) {
		super(name, max, b);
		rules = new GameRules();
	}

	public double decideBet() {
		PokerHand pokerHand = rules.processHand(hand);
		double betPercent = pokerHand.getHandValue() / 10.0 + Math.random() * 0.1 - 0.1;
		return getBalance() * betPercent;
	}

	public int[] decideDiscard() {
		PokerHand pokerHand = rules.processHand(hand);

		int size = 0;
		int v = pokerHand.getHandValue();
		if(v <= 1) { // High Card - discard all
			int[] discard = {0, 1, 2, 3, 4};
			return discard;
		}
		if(v == 2) { // One Pair - discard excess
			int two = pokerHand.getTwoOfAKind()[0];
			int[] discard = new int[3];
			for(int i = 0; i < hand.length; i++) {
				if(hand[i].getFaceValue() != two) {
					discard[size++] = i;
				}
			}
			return discard;
		}
		if(v == 4) { // Three Of A Kind - discard excess
			int three = pokerHand.getThreeOfAKind()[0];
			int[] discard = new int[2];
			for(int i = 0; i < hand.length; i++) {
				if(hand[i].getFaceValue() != three) {
					discard[size++] = i;
				}
			}
			return discard;
		}

		return new int[0];
	}
}