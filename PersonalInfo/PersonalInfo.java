import java.util.ArrayList;

public class PersonalInfo implements Comparable<PersonalInfo> {
	private String firstName;
	private String lastName;
	private int age = 0;
	private int votingAffiliation = 0;
	private Address address;
	private ArrayList<String> phoneNumbers;
	private ArrayList<String> emailAddresses;
	private ArrayList<String> websites;

	public PersonalInfo(String fn, String ln, String street, String city, String county, String state, String zip) {
		firstName = fn;
		lastName = ln;
		address = new Address(street, city, county, state, zip);
		phoneNumbers = new ArrayList<String>();
		emailAddresses = new ArrayList<String>();
		websites = new ArrayList<String>();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int a) {
		age = a;
	}

	public int getVotingAffiliation() {
		return votingAffiliation;
	}

	public void setVotingAffiliation(int vA) {
		votingAffiliation = vA;
	}

	public Address getAddress() {
		return address;
	}

	public ArrayList<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void addPhoneNumber(String pn) {
		phoneNumbers.add(pn);
	}

	public ArrayList<String> getEmailAddresses() {
		return emailAddresses;
	}

	public void addEmailAddress(String ea) {
		emailAddresses.add(ea);
	}

	public ArrayList<String> getWebsites() {
		return websites;
	}

	public void addWebsite(String w) {
		websites.add(w);
	}

	public int compareTo(PersonalInfo pi) {
		int diff = this.lastName.compareTo(pi.getLastName());
		if(diff == 0) {
			diff = this.firstName.compareTo(pi.getFirstName());
		}
		return diff;
	}

	public String toString() {
		String output = "Name: " + firstName + " " + lastName + "\n - Age: " + age + "\n - Voting Affiliation: " + votingAffiliation + "\n - Address: " + address + "\n - Phone Numbers: ";
		for(String pn : phoneNumbers) {
			output += pn + ", ";
		}
		output = output.substring(0, output.length() - 2) + "\n - Email Addresses: ";
		for(String ea : emailAddresses) {
			output += ea + ", ";
		}
		output = output.substring(0, output.length() - 2) + "\n - Websites: ";
		for(String ea : emailAddresses) {
			output += ea + ", ";
		}

		return output.substring(0, output.length() - 2);
	}
}