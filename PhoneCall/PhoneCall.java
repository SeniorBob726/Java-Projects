import java.util.ArrayList;

public abstract class PhoneCall {
	private long phoneNumber;
	private int duration;

	public PhoneCall(int min) {
		phoneNumber = (long) (Math.random() * Math.pow(10, 10));
		duration = min;
	}

	public abstract double getRate();

	public int getDuration() {
		return duration;
	}

	private int calculateCost() {
		return (int) (duration * getRate());
	}

	public String toString() {
		return String.format("%010d - Duration: %d Rate: %.1f Cost: %d", phoneNumber, duration, getRate(), calculateCost());
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