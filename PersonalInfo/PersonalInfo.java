import java.util.ArrayList;

public class PersonalInfo {
	private String firstName;
	private String lastName;
	private Address address;
	private ArrayList<String> phoneNumbers;
	private ArrayList<String> emailaddress;
	private ArrayList<String> website;

	public PersonalInfo(String fn, String ln, String street, String city, String state, String county) {
		firstName = fn;
		lastName = ln;
		address = new Address(street, city, state, county);
		phoneNumbers = new ArrayList<String>();
		emailaddress = new ArrayList<String>();
		website = new ArrayList<String>();
	}
}