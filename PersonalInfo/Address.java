public class Address {
	private String street;
	private String city;
	private String state;
	private String county;

	public Address(String str, String cty, String st, String cnty) {
		street = str;
		city = cty;
		state = st;
		county = cnty;
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