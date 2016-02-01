public class Employee extends StaffMember {
	private String ssn;
	private double wage;

	public Employee(String name, String address, String phoneNumber, String s, double w) {
		setName(name);
		setAddress(address);
		setPhoneNumber(phoneNumber);
		ssn = s;
		wage = w;
	}

	public void setSSN(String s) {
		ssn = s;
	}

	public void setWage(double w) {
		wage = w;
	}

	public String getSSN() {
		return ssn;
	}

	public double getWage() {
		return wage;
	}

	public double pay() {
		return wage;
	}

	public String toString() {
		return super.toString() + "\nSSN: " + ssn;
	}
}