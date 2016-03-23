import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Bar extends Rectangle2D.Double {
	private static final double length = 40;
	private static final double width = 5;
	private static double speed = 1.0;

	public Bar(double x, double y, double speed) {
		super(x, y, width, length);
		this.speed = speed;
	}

	public void update() {
		setRect(getX() - speed, getY(), width, length);
	}

	public void k(){speed=0.4;}
}