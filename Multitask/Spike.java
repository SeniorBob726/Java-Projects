import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;
import java.awt.geom.AffineTransform;

public class Spike extends Path2D.Double {
	private AffineTransform at;
	private final double direction;
	private final double speed;
	private final double radius;

	private final double tc;
	private final double ts;

	public Spike(double x, double y, double dir, double sp, double r) {
		at = new AffineTransform();
		direction = dir;
		speed = sp;
		radius = r;

		tc = speed * Math.cos(direction * Math.PI / 180);
		ts = speed * Math.sin(direction * Math.PI / 180);

		draw(x, y);
	}

	public Spike(double x, double y, double dir, double sp) {
		this(x, y, dir, sp, 12);
	}

	public double getSpeed() {
		return speed;
	}

	public void draw(double x, double y) {
		double rc = radius * tc / speed;
		double rs = radius * ts / speed;

		moveTo(rc + x, rs + y);
		lineTo(-rc - rs + x, rc - rs + y);
		lineTo(-rc + rs + x, -rc - rs + y);
		closePath();
	}

	public void update() {
		at.setToTranslation(tc, ts);
		transform(at);
	}
}