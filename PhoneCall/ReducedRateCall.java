public class ReducedRateCall extends LongDistanceCall {
	public ReducedRateCall(int min) {
		super(min);
	}

	public double getRate() {
		return super.getRate() / 2.0;
	}

	public String toString() {
		return super.toString() + "RR";
	}
}