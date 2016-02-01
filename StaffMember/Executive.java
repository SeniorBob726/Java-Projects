public class Executive extends Employee {
	private double bonus;

	public Executive(String name, String address, String phoneNumber, String ssn, double wage) {
		super(name, address, phoneNumber, ssn, wage);
		bonus = 0.0;
	}

	public void awardBonus(double b) {
		bonus = b;
	}

	public double pay() {
		return wage + bonus;
	}
}