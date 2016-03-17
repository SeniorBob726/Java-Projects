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

	public int compareTo(Address a) {
		int diff = this.state.compareTo(a.getState());
		if(diff == 0) {
			diff = this.county.compareTo(a.getCounty());
		}
		if(diff == 0) {
			diff = this.city.compareTo(a.getCity());
		}
		if(diff == 0) {
			diff = this.street.compareTo(a.getStreet());
		}
		return diff;
	}

	public String toString() {
		return String.format("%s %s, %s, %s %s", street, city, county, state, zipcode);
	}
}