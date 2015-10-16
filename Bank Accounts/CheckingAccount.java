public class CheckingAccount extends BankAccount {
	private double fee;

	public CheckingAccount(String name, String address, int number, double b, double f) {
		super(name, address, number, b);
		fee = f;
	}

	public boolean withdraw(double amount) {
		super.withdraw(amount + fee);
		return false;
	}
}