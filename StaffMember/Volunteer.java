public class Volunteer extends StaffMember {
	public Volunteer(String name, String address, String phoneNumber) {
		setName(name);
		setAddress(address);
		setPhoneNumber(phoneNumber);
	}

	public double pay() {
		return 0.0;
	}
}