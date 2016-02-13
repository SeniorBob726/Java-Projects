import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.AffineTransform;

public class Spike extends Path2D.Double {
	private AffineTransform transform;

	public Spike(double direction, double length) {
		direction *= Math.PI / 180;
		double base = 2 * length / 3;
		double r = length / 2;
		double c = Math.cos(direction);
		double s = Math.sin(direction);
		moveTo(r * c, r * s);
		lineTo(-r * c - length / 3 * s, -r * s + length / 3 * c);
		lineTo(-r * c + length / 3 * s, -r * s - length / 3 * c);
		closePath();

		transform = new AffineTransform();
	}

	public Spike(double direction) {
		this(direction, 30);
	}

	public void setTranslation(double x, double y) {
		transform.setToTranslation(x, y);
		transform(transform);
	}
}