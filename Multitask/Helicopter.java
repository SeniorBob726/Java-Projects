import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Helicopter extends MiniGame {
	private static final Color bgColor = new Color(192, 230, 192);

	private long nextBar;
	private static final double gravity = 0.05;

	private Polygon helicopter;
	private double helicopterPosition;
	private double helicopterVelocity;

	private Bar[] bars; // Contains active bars

	public Helicopter() {
		// Store base graphics
		int[] xPoints = {8, -8, -8};
		int[] yPoints = {0, 8, -8};
		helicopter = new Polygon(xPoints, yPoints, 3);

		nextBar = (long) (1.5 * 1000.0);
		bars = new Bar[5];

		reset();

		setFocusable(false);
		setBackground(bgColor);
	}

	public Bar createBar() {
		double x = getWidth() / 2;
		double y = (Math.random() - 0.5) * getHeight();
		return new Bar(x, y);
	}

	public void reset() {
		System.out.println("Reset - Helicopter");
		setBackground(bgColor);

		helicopterPosition = 0;
		helicopterVelocity = 0;
	}

	public void increaseLift() {
		helicopterVelocity -= 0.12;
	}

	public void update(long elapsedms) {
		helicopterPosition += helicopterVelocity;
		helicopterVelocity += gravity;
		helicopterVelocity = clamp(helicopterVelocity, -2, 2);

		double topHeight = -getHeight() / 2 + 8;
		double bottomHeight = getHeight() / 2 - 8;
		if(helicopterPosition < topHeight || helicopterPosition > bottomHeight) {
			helicopterVelocity = 0;
		}
		helicopterPosition = clamp(helicopterPosition, topHeight, bottomHeight);

		for(int i = 0; i < bars.length; i++) {
			Rectangle2D hB = helicopter.getBounds();
			if(bars[i] == null && elapsedms >= nextBar) {
				bars[i] = createBar();
				nextBar += Math.random() * 4000 + 1000;
			}
			else if(bars[i] != null && bars[i].intersects(hB.getX(), hB.getY(), hB.getWidth(), hB.getHeight())) {
				bars[i] = null;
			}
		}
	}

	public void pause(boolean paused) {
		if(paused) {
			System.out.println("Pause - Helicopter");
		}
		else {
			System.out.println("Resume - Helicopter");
		}
	}

	public boolean gameOver() {
		//System.out.println("Game Over - Helicopter");
		return false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.translate(centerX, centerY); // Set Graphics2D transform origin to center of panel

		g2d.setColor(new Color(0, 153, 0));
		g2d.fillPolygon(helicopter);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		for(Bar bar : bars) {
			if(bar != null) {
				g2d.draw(bar);
			}
		}
		g2d.dispose();
	}
}