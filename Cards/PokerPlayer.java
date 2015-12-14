public class PokerPlayer extends Player {
	private double balance;

	public PokerPlayer(String name, int max, double b) {
		super(name, max);
		balance = b;
	}

	public double getBalance() {
		return balance;
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