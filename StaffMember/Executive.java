public class Executive extends Employee {
	public Executive(String name, String address, String phoneNumber, String ssn, double wage) {
		super(name, address, phoneNumber, ssn, wage);
	}

	public void awardBonus(double bonus) {
		setWage(getWage() + bonus);
	}
}