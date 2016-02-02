public class LocalCall extends PhoneCall {
	private double rate;

	public LocalCall(int min, double r) {
		super(min);
		rate = r;
	}

	public double getRate() {
		return rate;
	}
}