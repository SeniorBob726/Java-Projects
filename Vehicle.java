public class Vehicle {
	private double speed;
	private int bearing;
	private double[] position;

	public Vehicle() {
		this(100, 0, 0);
	}

	public Vehicle(double s) {
		this(s, 0, 0);
	}

	public Vehicle(double s, double x, double y) {
		speed = s;
		bearing = 0;
		position[0] = x;
		position[1] = y;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double s) {
		speed = s;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double x, double y) {
		position[0] = x;
		position[1] = y;
	}

	public int getBearing() {
		return bearing;
	}

	public void turnRight(int deg) {
		bearing -= deg;
	}

	public void turnRight(int deg) {
		bearing -= deg;
	}

	public void turnLeft(int deg) {
		bearing += deg;
	}

	public void moveForward() {
		position[0] += speed * Math.cos(bearing);
		position[1] += speed * Math.sin(bearing);
	}

	public void moveBackward() {
		position[0] -= speed * Math.cos(bearing);
		position[1] -= speed * Math.sin(bearing);
	}

	public String toString() {
		return "The vehicle is at (" + position[0] + ", " + position[1] + ") with speed " + speed + " and bearing " + bearing + " degrees";
	}
}