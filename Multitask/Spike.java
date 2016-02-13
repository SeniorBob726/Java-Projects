import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class Spike extends Path2D.Double {
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
	}

	public Spike(double direction) {
		this(direction, 30);
	}
}