import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Bar extends Rectangle2D.Double {
	private static final double speed = 1;
	private static final double length = 40;
	private static final double width = 5;

	public Bar(double x, double y) {
		super(x, y, width, length);
	}

	public void update() {
		setRect(getX() - 1, getY(), width, length);
	}
}