public class Cylinder {
	private Circle base;
	private double height;

	public Cylinder(int r, double h) {
		base = new Circle(r);
		height = h;
	}

	public void setHeight(double h) {
		height = h;
	}

	public double getVolume() {
		return base.getArea() * height;
	}

	public String toString() {
		return "The radius of the base is " + base.getRadius() + " units, the height is " + String.format("%.2f", height) + " units and the volume is " + String.format("%.2f", getVolume()) + " units squared.";
	}
}