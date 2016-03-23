public class Airplane extends Vehicle {
	private double altitude;

	public Airplane() {
		this(200, 0, 0, 0);
	}

	public Airplane(double s) {
		this(s, 0, 0, 0);
	}

	public Airplane(double s, double x, double y) {
		this(s, x, y, 0);
	}

	public Airplane(double s, double x, double y, double a) {
		super(s, x, y);
		altitude = a;
	}

	public double getAltitude() {
		return altitude;
	}

	public void ascend(double a) {
		altitude += a;
	}

	public void descend(double d) {
		altitude -= d;
	}

	public void moveBackward() {
		// Do nothing
	}

	public String toString() {
		return super.toString() + " and altitude " + altitude + " units";
	}
}