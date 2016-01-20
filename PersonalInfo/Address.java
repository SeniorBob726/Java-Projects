public class Address {
	private String street;
	private String city;
	private String state;
	private String county;
	private int zipcode;

	public Address(String str, String cty, String st, String cnty, int zip) {
		street = str;
		city = cty;
		state = st;
		county = cnty;
		zipcode = zip;
	}

	private String getStreet() {
		return street;
	}

	private String getCity() {
		return city;
	}

	private String getState() {
		return state;
	}

	private String getCounty() {
		return county;
	}
}