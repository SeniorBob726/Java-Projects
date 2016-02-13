import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class Spike extends Path2D.Double {
	private int base = 24;

	public Spike(int direction) { // 0 = up, 1 = right, 2 = down, 3 = left
		moveTo(points[0].getX(), points[0].getY());
		lineTo(points[1].getX(), points[1].getY());
		lineTo(points[2].getX(), points[2].getY());
		closePath();
	}
}