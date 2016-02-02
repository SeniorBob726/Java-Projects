public class ReducedRateCall extends LongDistanceCall {
	public ReducedRateCall(int min) {
		super(min);
	}

	public double getRate() {
		return 3.0;
	}

	public String toString() {
		return super.toString() + "RR";
	}
}