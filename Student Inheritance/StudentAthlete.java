public class StudentAthlete extends Student {
	public String sport;

	public StudentAthlete(String n, int c, String s) {
		super(n, c);
		sport = s;
	}

	public String getSport() {
		return sport;
	}

	public void getSport(String s) {
		sport = s;
	}

	public String toString() {
		return super.toString() + "\nSport: " + getSport();
	}
}