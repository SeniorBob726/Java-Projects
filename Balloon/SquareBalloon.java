import java.awt.Color;
import java.awt.Graphics;

public class SquareBalloon extends Balloon {
	public SquareBalloon() {
		super();
	}

	public SquareBalloon(int x, int y, int r, Color c) {
		super(x, y, r, c);
	}

	public double distance(int x, int y) {
		double dx = x - getX();
		double dy = y - getY();
		
		return Math.max(Math.abs(dx), Math.abs(dy));
	}

	public void inflate(int percentage) {
		setRadius((int) (getRadius() * Math.pow(2, 1 / 3.0)));
	}

	public void draw(Graphics g, boolean makeItFilled) {
		g.setColor(getColor());
		if (makeItFilled) {
			g.fillRect(getX() - getRadius(), getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
		}
		else {
			g.drawRect(getX() - getRadius(), getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
		}
	}
}