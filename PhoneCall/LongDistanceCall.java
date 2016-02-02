public class LongDistanceCall extends PhoneCall {
	public LongDistanceCall(int min) {
		super(min);
	}

	public double getRate() {
		return 6.0;
	}

	public String toString() {
		return super.toString() + " LD";
	}
}