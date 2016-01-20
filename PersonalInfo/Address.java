public class Address {
	private String street;
	private String city;
	private String county;
	private String state;
	private int zipcode;

	public Address(String str, String cty, String cnty, String st, int zip) {
		street = str;
		city = cty;
		county = cnty;
		state = st;
		zipcode = zip;
	}

	private String getStreet() {
		return street;
	}

	private String getCity() {
		return city;
	}

	private String getCounty() {
		return county;
	}

	private String getState() {
		return state;
	}
}