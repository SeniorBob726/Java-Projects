import java.util.Scanner;

public class Bank {
	private BankAccount[] accounts;
	private int numberCustomers;

	public Bank(int bankSize) {
		accounts = new BankAccount[bankSize];
		numberCustomers = 0;
	}

	public BankAccount[] getAccounts() {
		return accounts;
	}

	public int getNumberCustomers() {
		return numberCustomers;
	}

	public boolean elementExistsInArray(int[] array, int element) {
		for(int i : array) {
			if(i == element) {
				return true;
			}
		}
		return false;
	}

	public int addAccount(String name, String address, double balance) {
		if(accounts.length > numberCustomers) {
			int[] accountNumbers = new int[numberCustomers];
			for(int i = 0; i < numberCustomers; i++) {
				accountNumbers[i] = accounts[i].getAccountNumber();
			}

			int number;
			do {
				number = (int)(Math.random() * 1000000);
			} while(!elementExistsInArray(accountNumbers, number));

			accounts[numberCustomers] = new BankAccount(name, address, number, balance);
			numberCustomers++;
			return number;
		}
		return 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Create a bank with a maximum size of: ");
		Bank bank = new Bank(scanner.nextInt());

		System.out.print("\n");
		System.out.println("Welcome to the Bank! Type any of the following commands to start.");
		System.out.println("\tAdd - Add an account to the bank");
		System.out.println("\tDeposit - Deposit money into an account");
		System.out.println("\tWithdraw - Withdraw money from an account");
		System.out.println("\tExit - Exit the bank interface");

		while(true) {
			System.out.print("> ");
			String input = scanner.next().toLowerCase();

			if(input.equals("exit")) {
				System.out.println("Goodbye!");
				break;
			}

			if(input.equals("add")) {
				if(bank.getAccounts().length > bank.getNumberCustomers()) {
					System.out.print("Please enter your name: ");
					String name = scanner.next();
					System.out.print("Please enter your address: ");
					String address = scanner.next();
					System.out.println("Your account number is: " + bank.addAccount(name, address, 0));
				}
				else {
					System.out.println("Sorry, the bank is at max capacity.");
				}
			}
		}
	}

	/*int addAccount(String name, String address, double balance) - instantiates next element of the accounts[] array to a new BankAccount with this information. Updates numberCustomers. Return account number to user.  Ensure you don't go over capacity.
	boolean makeWithdrawal(int acctNum, double amt) - makes a withdrawal from the correct account based on account number. Returns success or failure.
	boolean makeDeposit(int acctNum, double amt) - makes a deposit from the correct account based on account number. Returns success or failure.
	String showAccount(int acctNum) - returns in String form the account information related to the accountNum
	toString() prints out all accounts
	void manageCustomers() - contains a while loop that displays the main menu for the Bank. Last 3 options require user to enter acct number.  Menu should include:
	Create an Account  -> call addAccount()
	Make a withdrawal -> call makeWithdrawal()
	Make a deposit -> call makeDeposit()
	View account information -> call showAccount()*/
}