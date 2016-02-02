import java.util.ArrayList;

public abstract class PhoneCall {
	private int phoneNumber;
	private int duration;

	public PhoneCall(int min) {
		phoneNumber = (int) (Math.random() * Math.pow(1, 10));
		duration = min;
	}

	public abstract double getRate();

	private int getDuration() {
		return duration;
	}

	public String toString() {
		return "";
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