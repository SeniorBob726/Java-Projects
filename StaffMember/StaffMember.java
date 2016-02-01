public abstract class StaffMember {
	private String name;
	private String address;
	private String phoneNumber;

	public abstract double pay();

	public void setName(String n) {
		name = n;
	}

	public void setAddress(String a) {
		address = a;
	}

	public void setPhoneNumber(String pn) {
		phoneNumber = pn;
	}

	public String toString() {
		return "Name: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNumber;
	}
}