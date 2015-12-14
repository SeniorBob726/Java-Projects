public class ComputerPokerPlayer extends PokerPlayer {
	private GameRules rules;

	public ComputerPokerPlayer(String name, int max, double b) {
		super(name, max, b);
		rules = new GameRules();
	}

	public double decideBet(Card[] hand) {
		PokerHand pokerHand = rules.processHand(hand);

		return 0;
	}

	public int[] decideDiscard(Card[] hand) {
		PokerHand pokerHand = rules.processHand(hand);
		return new int[0];
	}
}