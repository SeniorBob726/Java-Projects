/**
* This class implements a vendor that sells one kind
* of items. A vendor carries out sales transactions.
*/
public class Vendor {
	private int change = 0;
	private int deposit = 0;
	private int pricePerUnit = 0;
	private int amountStocked = 0;
	/**
	* Constructs a Vendor
	* @param price price of a single item in cents (int)
	* @param stock number of items to place in stock (int)
	*/
	public Vendor(int price, int stock) {
		pricePerUnit = price;
		amountStocked = stock;
	}

	/**
	* Sets the quantity of items in stock.
	* @param qty number of items to place in stock (int)
	*/
	public void setStock(int qty) {
		amountStocked = qty;
	}

	/**
	* Returns the number of items currently in stock.
	* @return number of items currently in stock (int)
	*/
	public int getStock() {
		return amountStocked;
	}

	/**
	* Adds a specified amount (in cents) to the
	* deposited amount.
	* @param amount number of cents to add to the deposit (int)
	*/
	public void addMoney(int amount) {
		deposit += amount;
	}

	/**
	* Returns the currently deposited amount (in cents).
	* @return number of cents in the current deposit (int)
	*/
	public int getDeposit() {
		return deposit;
	}

	/**
	* Implements a sale. If there are items in stock and
	* the deposited amount is greater than or equal to
	* the single item price, then adjusts the stock and
	* calculates and sets change, sets deposit to 0 and
	* returns true; otherwise refunds the whole deposit
	* (moves it into change) and returns false.
	* @return true for a successful sale, false otherwise (boolean)
	*/
	public boolean makeSale() {
		if(amountStocked > 0 && deposit >= pricePerUnit) {
			amountStocked--;
			change = deposit - pricePerUnit;
			deposit = 0;
			return true;
		}
		change = deposit;
		deposit = 0;
		return false;
	}

	/**
	* Returns and zeroes out the amount of change (from
	* the last sale or refund).
	* @return number of cents in the current change (int)
	*/
	public int getChange() {
		return change;
	}
}
