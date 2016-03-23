public class Point {
	private double x;
	private double y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(double xVal, double yVal) {
		x = xVal;
		y = yVal;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double xVal) {
		x = xVal;
	}

	public void setY(double yVal) {
		y = yVal;
	}

	public void setCoordinates(double xVal, double yVal) {
		x = xVal;
		y = yVal;
	}

	public String toString() {
		return "This point is at (" + x + ", " + y + ")";
	}
}