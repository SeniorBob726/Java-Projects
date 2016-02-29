import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;

public class Spike extends Path2D.Double {
	private Point2D position;
	private final double direction;
	private final double radius;
	private double speed;

	private final double c;
	private final double s;

	public Spike(double x, double y, double direction, double speed, double radius) {
		this.position = new Point2D.Double(x, y);
		this.direction = direction % 360;
		this.speed = speed;
		this.radius = radius;

		c = Math.cos(direction * Math.PI / 180);
		s = Math.sin(direction * Math.PI / 180);

		draw(x, y);
	}

	public Spike(double x, double y, double direction, double speed) {
		this(x, y, direction, speed, 8);
	}

	public Point2D getPosition() {
		return position;
	}

	public double getDirection() {
		return direction;
	}

	public void draw(double x, double y) {
		double rc = radius * c;
		double rs = radius * s;

		reset();
		moveTo(rc + x, rs + y);
		lineTo(-rc - rs + x, rc - rs + y);
		lineTo(-rc + rs + x, -rc - rs + y);
		closePath();
	}

	public void update() {
		position.setLocation(position.getX() + speed * c,
							 position.getY() + speed * s);
		draw(position.getX(), position.getY());
	}

	public void k(){speed=0.4;}
}