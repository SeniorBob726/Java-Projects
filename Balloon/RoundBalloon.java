import java.awt.Color;
import java.awt.Graphics;

public class RoundBalloon extends Balloon {
	public RoundBalloon() {
		super();
	}

	public RoundBalloon(int x, int y, int r, Color c) {
		super(x, y, r, c);
	}

	public void inflate(int percentage) {
		double r3 = Math.pow(getRadius(), 3);
		r3 *= 1 + (percentage / 100.0);
		setRadius((int) Math.pow(r3, 1 / 3.0));
	}

	public void draw(Graphics g, boolean makeItFilled) {
		g.setColor(getColor());
		if (makeItFilled) {
			g.fillOval(getX() - getRadius(), getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
		}
		else {
			g.drawOval(getX() - getRadius(), getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
		}
	}
}