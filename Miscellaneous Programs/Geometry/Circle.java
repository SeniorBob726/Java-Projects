public class Circle {
	private int radius;

	public Circle(int r) {
		radius = r;
	}

	public void setRadius(int r) {
		radius = r;
	}

	public int getRadius() {
		return radius;
	}

	public double getArea() {
		return Math.PI * radius * radius;
	}

	public String toString() {
		return "The radius is " + radius + " units and the area is " + String.format("%.2f", getArea()) + " units squared.";
	}
}