public class Reserved extends Ticket {
	private int days;

	public Reserved(int d) {
		days = d;
	}

	public double getPrice() {
		return days < 10 ? 40.0 : 30.0;
	}
}