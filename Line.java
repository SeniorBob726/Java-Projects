public class Line {
	private Point pt1;
	private Point pt2;
	
	public Line(double x1, double y1, double x2, double y2) {
		pt1 = new Point(x1, y1);
		pt2 = new Point(x2, y2);
	}

	public Point getPoint1() {
		return pt1;
	}

	public Point getPoint2() {
		return pt2;
	}

	public void setPoint1(double xVal, double yVal) {
		pt1.setCoordinates(xVal, yVal);
	}

	public void setPoint2(double xVal, double yVal) {
		pt2.setCoordinates(xVal, yVal);
	}

	public double getLength() {
		return Math.sqrt(Math.pow(pt2.getX() - pt1.getX(), 2) + Math.pow(pt2.getY() - pt1.getY(), 2));
	}

	public double getSlope() {
		return (pt2.getX() - pt1.getX()) / (pt2.getY() - pt1.getY());
	}

	public double getYInt() {
		return pt1.getY() - pt1.getX() * getSlope();
	}

	public String toString() {
		return "The equation for this line is y = " + getSlope() + "x + " + getYInt();
	}
}