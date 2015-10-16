public class SavingsAccount extends BankAccount {
	private double interestRate;

	public SavingsAccount(String name, String address, int number, double b, double rate) {
		super(name, address, number, b);
		interestRate = rate;
	}

	public double compoundInterest(int n, double t) {
		return getBalance() * Math.pow(1 + interestRate / n, n * t);
	}
}