public class ComputerPokerPlayer extends PokerPlayer {
	private GameRules rules;

	public ComputerPokerPlayer(String name, int max, double b) {
		super(name, max, b);
		rules = new GameRules();
	}

	public double bet(Card[] hand) {
		rules.processHand(hand);
	}

	public int[] playHand() {
		rules.processHand(hand);
	}
}