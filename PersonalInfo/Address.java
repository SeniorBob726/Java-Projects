public class Address {
	private String street;
	private String city;
	private String county;
	private String state;
	private String zipcode;

	public Address(String str, String cty, String cnty, String st, String zip) {
		street = str;
		city = cty;
		county = cnty;
		state = st;
		zipcode = zip;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getCounty() {
		return county;
	}

	public String getState() {
		return state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String toString() {
		return String.format("%s %s, %s, %s %s", street, city, county, state, zipcode);
	}
}