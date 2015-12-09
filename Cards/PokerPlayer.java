public class PokerPlayer extends Player {
	private double balance;

	public PokerPlayer(double b) {
		super(name, max);
		balance = b;
	}

	public void addWinnings(double amount) {
		balance += amount;
	}

	public boolean bet(double amount) {
		if(balance >= amount) {
			balance -= amount;
			return true;
		}

		return false;
	}
}