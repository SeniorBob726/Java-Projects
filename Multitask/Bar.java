import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Bar extends Rectangle2D.Double {
	private final static double speed = 1;
	private final static double length = 30;
	private final static double width = 5;

	public Bar(double x, double y) {
		super(x, y, width, length);
	}

	public void update() {
		setRect(getX(), getY() - 1, width, length);
	}
}