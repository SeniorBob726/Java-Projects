import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class Spike extends Path2D.Double {
	public Spike(double direction, double length) {
		double base = 2 * length / 3;
		double r = length / 2;
		moveTo(r * Math.cos(direction), r * Math.sin(direction));
		lineTo(-r * Math.cos(direction) - length / 3 * Math.sin(direction), -r * Math.sin(direction) + length / 3 * Math.cos(direction));
		lineTo(-r * Math.cos(direction) + length / 3 * Math.sin(direction), -r * Math.sin(direction) - length / 3 * Math.cos(direction));
		closePath();
	}

	public Spike(double direction) {
		this(direction, 30);
	}
}