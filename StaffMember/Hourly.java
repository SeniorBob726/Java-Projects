public class Hourly extends Employee {
	private int hours;

	public Hourly(String name, String address, String phoneNumber, String ssn, double wage) {
		super(name, address, phoneNumber, ssn, wage);
		hours = 0;
	}

	public void addHours(int h) {
		hours += h;
	}

	public double pay() {
		double pay = getWage() * hours;
		hours = 0;
		return pay;
	}
}