import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;
import java.awt.geom.AffineTransform;

public class Spike extends Path2D.Double {
	private AffineTransform transform;
	private Point2D position;
	private double direction;
	private double speed;
	private double radius;

	public Spike(double x, double y, double dir, double sp, double r) {
		transform = new AffineTransform();
		position = new Point2D.Double(x, y);
		direction = dir * Math.PI / 180;
		speed = sp;
		radius = r;

		draw();
	}

	public Spike(double x, double y, double dir, double sp) {
		this(x, y, dir, sp, 12);
	}

	public void draw() {
		double rc = radius * Math.cos(direction);
		double rs = radius * Math.sin(direction);

		moveTo(rc, rs);
		lineTo(-rc - rs, rc - rs);
		lineTo(-rc + rs, -rc - rs);
		closePath();

		transform.setToTranslation(position.getX(), position.getY());
		transform(transform);
	}

	public void update() {
		position.setLocation(speed * Math.cos(direction), speed * Math.sin(direction));

		transform.setToTranslation(position.getX(), position.getY());
		transform(transform);
	}
}