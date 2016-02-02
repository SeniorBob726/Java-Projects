import java.util.ArrayList;

public abstract class PhoneCall {
	protected int phoneNumber;
	protected double minutes;
	protected double costPerMinute;

	public PhoneCall() {
		phoneNumber = (int) (Math.random() * Math.pow(1, 10));
		minutes = 0;
	}

	public double costForCall() {
		return minutes * costPerMinute;
	}

	public static void main(String[] args) {
		ArrayList<PhoneCall> calls = new ArrayList<PhoneCall>();
		calls.add(new LocalCall(10, 2.5));
		calls.add(new LongDistanceCall(3));
		calls.add(new ReducedRateCall(8));
		for(PhoneCall call : calls) {
			System.out.println(call);
		}
	}
}