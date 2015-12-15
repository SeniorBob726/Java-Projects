public class ComputerPokerPlayer extends PokerPlayer {
	private GameRules rules;

	public ComputerPokerPlayer(String name, int max, double b) {
		super(name, max, b);
		rules = new GameRules();
	}

	public double decideBet(Card[] hand) {
		PokerHand pokerHand = rules.processHand(hand);
		double betPercent = pokerHand.getHandValue() / 10.0 + Math.random() * 0.1 - 0.1;
		return getBalance() * betPercent;
	}

	public int[] decideDiscard(Card[] hand) {
		PokerHand pokerHand = rules.processHand(hand);
		return new int[0];
	}
}