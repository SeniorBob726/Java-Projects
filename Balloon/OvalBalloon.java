import java.awt.Color;
import java.awt.Graphics;

public class OvalBalloon extends Balloon {
	public OvalBalloon() {
		super();
	}

	public OvalBalloon(int x, int y, int r, Color c) {
		super(x, y, r, c);
	}

	public double distance(int x, int y) {
		double dx = x - getX();
		dx *= 2;
		double dy = y - getY();
		return Math.sqrt(dx * dx + dy * dy);
	}

	public void draw(Graphics g, boolean makeItFilled) {
		g.setColor(getColor());
		if (makeItFilled) {
			g.fillOval(getX() - getRadius() / 2, getY() - getRadius(), getRadius(), 2 * getRadius());
		}
		else {
			g.drawOval(getX() - getRadius() / 2, getY() - getRadius(), getRadius(), 2 * getRadius());
		}
	}
}