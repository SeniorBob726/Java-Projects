public class Employee extends StaffMember {
	protected String ssn;
	protected double wage;

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

	public double pay() {
		return wage;
	}

	public String toString() {
		return super.toString() + "\nSSN: " + ssn;
	}
}