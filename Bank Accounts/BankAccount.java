public class BankAccount {
	private String accountName;
	private String accountAddress;
	private int accountNumber;
	private double balance;


	public BankAccount(String name, String address, int number, double b) {
		accountName = name;
		accountAddress = address;
		accountNumber = number;
		balance = b;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String name) {
		accountName = name;
	}

	public String getAddress() {
		return accountAddress;
	}

	public void setAddress(String address) {
		accountAddress = address;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int number) {
		accountNumber = number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double b) {
		balance = b;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public boolean withdraw(double amount) {
		if(balance >= amount) {
			balance -= amount;
			return true;
		}
		
		return false;
	}

	public String toString() {
		return "Account " + accountNumber + " is owned by " + accountName + " at " + accountAddress + " and has a balance of $" + balance;
	}
}