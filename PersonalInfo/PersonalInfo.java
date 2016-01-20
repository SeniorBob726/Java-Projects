import java.util.ArrayList;

public class PersonalInfo {
	private String firstName;
	private String lastName;
	private Address address;
	private ArrayList<String> phoneNumbers;
	private ArrayList<String> emailAddress;
	private ArrayList<String> website;

	public PersonalInfo(String fn, String ln, String street, String city, String state, String county) {
		firstName = fn;
		lastName = ln;
		address = new Address(street, city, state, county);
		phoneNumbers = new ArrayList<String>();
		emailAddress = new ArrayList<String>();
		website = new ArrayList<String>();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}

	public ArrayList<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public ArrayList<String> getEmailAddress() {
		return emailAddress;
	}

	public ArrayList<String> getWebsite() {
		return website;
	}
}